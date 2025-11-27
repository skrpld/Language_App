package com.example.language_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val firstName: String,
    val lastName: String,
    val passwordHash: String,
    val profilePicturePath: String
)