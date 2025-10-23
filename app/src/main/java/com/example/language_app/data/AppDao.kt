package com.example.language_app.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AppDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
}