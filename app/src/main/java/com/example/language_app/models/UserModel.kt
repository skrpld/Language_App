package com.example.language_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey
    val email: String,
    val firstName: String,
    val lastName: String,
    val pass: String
)
