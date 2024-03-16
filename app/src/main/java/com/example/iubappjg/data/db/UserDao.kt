package com.example.iubappjg.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.iubappjg.data.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email AND password = :password ")
    fun getUserByCredentials(email: String, password: String): UserModel?

    @Query("SELECT * FROM user")
    fun getUsers(): List<UserModel>

    @Insert
    fun insert(user: UserModel)

    @Delete
    fun delete(user: UserModel)

    //@Query("update user set email=:email, password=:password, name=:name, phone=:phone where uid=:uid")
    //fun update(uid: Long, email: String, password: String, name: String, phone: String): UserModel?

    @Update
    fun update(user: UserModel)
}