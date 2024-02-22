package com.ptotakkanan.jurnalkas.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.model.User
import com.ptotakkanan.jurnalkas.data.util.FirebaseCollections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class AppRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    suspend fun register(email: String, password: String): Flow<Resource<Unit>> = flow {
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

    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>> = flow {
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
}