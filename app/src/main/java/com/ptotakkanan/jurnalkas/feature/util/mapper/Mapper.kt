package com.ptotakkanan.jurnalkas.feature.util.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.ptotakkanan.jurnalkas.data.model.Category
import com.ptotakkanan.jurnalkas.data.model.Note
import com.ptotakkanan.jurnalkas.data.model.User
import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.domain.Wallet

object Mapper{
    fun DocumentSnapshot.toTransaction() = Transaction(
        transactionId = getString("transactionId") ?: "",
        walletId = getString("walletId") ?: "",
        title = getString("title") ?: "",
        description = getString("description") ?: "",
        imageUrl = getString("imageUrl") ?: "",
        nominal = getLong("nominal") ?: 0L,
        isIncome = getBoolean("income") ?: false,
        date = getString("date") ?: "",
    )

    fun DocumentSnapshot.toWallet() = Wallet (
        walletId = getString("walletId") ?: "",
        name = getString("name") ?: "",
        icon = getString("imageUrl") ?: "",
        balance = getLong("balance") ?: 0
    )

    fun DocumentSnapshot.toUser() = User(
        uid = getString("uid") ?: "",
        name = getString("name") ?: "",
        description = getString("description") ?: "",
        email = getString("email") ?: "",
        username = getString("name") ?: "",
        gender = getString("gender") ?: "",
        balance = getLong("balance") ?: 0,
        phone = getString("phone") ?: "",
        nik = getString("nik") ?: ""
    )

    fun DocumentSnapshot.toCategory() = Category(
        categoryId = getString("categoryId") ?: "",
        name = getString("name") ?: "",
        description = getString("description") ?: "",
        example = getString("example") ?: "",
        imageUrl = getString("imageUrl") ?: ""
    )

    fun DocumentSnapshot.toNote() = Note(
        title = getString("title") ?: "",
        imageUrl = getString("imageUrl") ?: "",
        nominal = getLong("nominal") ?: 0,
        isIncome = getBoolean("income") ?: false
    )
}