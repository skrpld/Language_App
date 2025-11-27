package com.example.language_app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.language_app.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Long): Flow<User>

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun authenticateUser(email: String, password: String): User?

    @Insert
    suspend fun insertUser(user: User): Long
}