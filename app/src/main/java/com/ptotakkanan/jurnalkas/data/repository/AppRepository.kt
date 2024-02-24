package com.ptotakkanan.jurnalkas.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.model.User
import com.ptotakkanan.jurnalkas.data.util.FirebaseCollections
import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.domain.Note
import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.domain.WalletDetail
import com.ptotakkanan.jurnalkas.feature.util.mapper.Mapper.toCategory
import com.ptotakkanan.jurnalkas.feature.util.mapper.Mapper.toNote
import com.ptotakkanan.jurnalkas.feature.util.mapper.Mapper.toTransaction
import com.ptotakkanan.jurnalkas.feature.util.mapper.Mapper.toUser
import com.ptotakkanan.jurnalkas.feature.util.mapper.Mapper.toWallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class AppRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    fun register(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                val user = User()
                user.uid = firebaseUser.uid

                firebaseUser.uid.let { uid ->
                    val userData = hashMapOf(
                        "uid" to user.uid,
                        "name" to user.name,
                        "description" to user.description,
                        "email" to user.email,
                        "username" to user.username,
                        "gender" to user.gender,
                        "balance" to user.balance,
                        "phone" to user.phone,
                        "nik" to user.nik
                    )
                    firestore.collection(FirebaseCollections.USER).document(uid).set(userData)
                        .await()
                }
                emit(Resource.Success(Unit))
            }
        } catch (e: Exception) {
            Log.d("Register", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun login(email: String, password: String): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading())
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result.user!!))
        } catch (e: Exception) {
            Log.d("Login", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun checkAuthStatus(): Boolean {
        return currentUser != null
    }

    fun fetchWallets(): Flow<Resource<List<Wallet>>> = flow {
        emit(Resource.Loading())
        val walletList = mutableListOf<Wallet>()
        try {
            val snapshot = firestore.collection(FirebaseCollections.WALLET).get().await()
            for (document in snapshot.documents) {
                val walletId = document.id
                val imageUrl = document.getString("imageUrl") ?: ""
                val name = document.getString("name") ?: ""
                val balance = document.getLong("balance") ?: 0L

                val wallet =
                    Wallet(walletId = walletId, name = name, icon = imageUrl, balance = balance)
                walletList.add(wallet)
            }
            emit(Resource.Success(walletList.toList()))
        } catch (e: Exception) {
            Log.d("Fetch Wallet", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun addTransaction(transaction: Transaction): Flow<Resource<Unit>> = channelFlow {
        updateWalletBalance(transaction).collectLatest { result ->
            when (result) {
                is Resource.Loading -> send(Resource.Loading())
                is Resource.Error -> send(Resource.Error(result.message))
                is Resource.Success -> {
                    try {
                        send(Resource.Loading())
                        val documentRef =
                            firestore.collection(FirebaseCollections.TRANSACTION).add(transaction)
                                .await()
                        val transactionId = documentRef.id
                        val updatedTransaction = transaction.copy(transactionId = transactionId)
                        documentRef.set(updatedTransaction).await()
                        send(Resource.Success(Unit))
                    } catch (e: FirebaseFirestoreException) {
                        Log.d("Add Transaction", e.message.toString())
                        send(Resource.Error(e.message))
                    }

                }
            }
        }

    }

    private fun updateWalletBalance(transaction: Transaction): Flow<Resource<Unit>> = flow {
        try {
            val walletDocRef =
                firestore.collection(FirebaseCollections.WALLET).document(transaction.walletId)
            val walletDoc = walletDocRef.get().await()

            if (walletDoc.exists()) {
                val currentBalance = walletDoc.getDouble("balance") ?: 0.0
                val newBalance =
                    if (transaction.isIncome)
                        currentBalance + transaction.nominal
                    else
                        currentBalance - transaction.nominal
                walletDocRef.update("balance", newBalance).await()
                Log.d("Update Balance", "Success")
            }
            emit(Resource.Success(Unit))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Update Balance", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun fetchWalletDetail(walletId: String): Flow<Resource<WalletDetail>> = flow {
        emit(Resource.Loading())
        try {
            val wallet = firestore.collection(FirebaseCollections.WALLET)
                .document(walletId)
                .get()
                .await()
                .toWallet()

            val transactions = firestore.collection(FirebaseCollections.TRANSACTION)
                .whereEqualTo("walletId", walletId)
                .get()
                .await()
                .documents.map { it.toTransaction() }

            Log.d("Transaction Wallet", transactions.size.toString())

            val income = transactions.filter { it.isIncome }.sumOf { it.nominal }
            val outcome = transactions.filter { !it.isIncome }.sumOf { it.nominal }

            val walletDetail = WalletDetail(wallet, income, outcome, transactions)
            emit(Resource.Success(walletDetail))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Wallet Detail", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun fetchTransactions(): Flow<Resource<List<Transaction>>> = flow {
        emit(Resource.Loading())
        try {
            val transactions = firestore.collection(FirebaseCollections.TRANSACTION)
                .get()
                .await()
                .map { it.toTransaction() }
            emit(Resource.Success(transactions))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Transaction", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun fetchBalance(): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            val balance = firestore.collection(FirebaseCollections.WALLET)
                .get()
                .await()
                .sumOf {
                    val balance = it.getLong("balance") ?: 0L
                    balance
                }
            emit(Resource.Success(balance))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Balance", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun fetchUserDetail(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val uid = currentUser!!.uid
            val user = firestore.collection(FirebaseCollections.USER)
                .document(uid)
                .get()
                .await()
                .toUser()

            emit(Resource.Success(user))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Balance", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun updateUserData(body: User): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val uid = currentUser!!.uid
            firestore.collection(FirebaseCollections.USER)
                .document(uid)
                .set(body)
                .await()
            emit(Resource.Success(Unit))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Update User", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun addNewCategory(body: com.ptotakkanan.jurnalkas.data.model.Category): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                val documentRef = firestore.collection(FirebaseCollections.CATEGORY)
                    .add(body)
                    .await()
                val categoryId = documentRef.id
                val updatedBody = body.copy(categoryId = categoryId)
                documentRef.set(updatedBody).await()
                emit(Resource.Success(Unit))
            } catch (e: FirebaseFirestoreException) {
                Log.d("Add Category", e.message.toString())
                emit(Resource.Error(e.message))
            }
        }

    fun fetchCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            val categories = firestore
                .collection(FirebaseCollections.CATEGORY)
                .get()
                .await()
                .map { it.toCategory() }

            emit(Resource.Success(categories.map { it.toDomain() }))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Categories", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    fun fetchCategoryDetail(categoryId: String): Flow<Resource<Category>> =
        flow {
            emit(Resource.Loading())
            try {
                val category = firestore
                    .collection(FirebaseCollections.CATEGORY)
                    .document(categoryId)
                    .get()
                    .await()
                    .toCategory()

                emit(Resource.Success(category.toDomain()))
            } catch (e: FirebaseFirestoreException) {
                Log.d("Fetch Category Detail", e.message.toString())
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)

    fun fetchCategoryImages(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            val categoryImages: List<String> = firestore
                .collection(FirebaseCollections.CATEGORY)
                .get()
                .await()
                .map { it["imageUrl"].toString() }

            emit(Resource.Success(categoryImages))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Categories", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun fetchNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val notes: List<com.ptotakkanan.jurnalkas.data.model.Note> = firestore
                .collection(FirebaseCollections.TRANSACTION)
                .get()
                .await()
                .map { it.toNote() }
            emit(Resource.Success(notes.map { it.toDomain() }))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Notes", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }

    fun searchNote(query: String): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val notes: List<com.ptotakkanan.jurnalkas.data.model.Note> = firestore
                .collection(FirebaseCollections.TRANSACTION)
                .get()
                .await()
                .map { it.toNote() }
                .filter { it.title.contains(query, ignoreCase = true) }
            emit(Resource.Success(notes.map { it.toDomain() }))
        } catch (e: FirebaseFirestoreException) {
            Log.d("Fetch Notes", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }
}