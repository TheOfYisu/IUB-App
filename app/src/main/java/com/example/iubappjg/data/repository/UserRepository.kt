package com.example.iubappjg.data.repository

import android.content.Context
import android.util.Log
import com.example.iubappjg.data.db.AppDataBase
import com.example.iubappjg.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (context : Context){
    private val database=AppDataBase.getInstance(context)

    suspend fun validateLogin(user:String, password:String):Boolean {
        return withContext(Dispatchers.IO){
            val userFromDb = database.userDao().getUserByCredentials(user, password)
            userFromDb!=null
        }
    }

    suspend fun getUser(user: String, password: String): UserModel? {
        return withContext(Dispatchers.IO) {
            val userFromDb = database.userDao().getUserByCredentials(user, password)
            userFromDb
        }
    }


    suspend fun getUsers():List<UserModel>{
        return withContext(Dispatchers.IO){
            database.userDao().getUsers()
        }
    }

    suspend fun insertUser(user:UserModel){
        withContext(Dispatchers.IO){
            database.userDao().insert(user)
        }
    }

    suspend fun deleteUser(user:UserModel){
        withContext(Dispatchers.IO){
            database.userDao().delete(user)
        }
    }

    suspend fun updateUser(user: UserModel ) {
        withContext(Dispatchers.IO){
            database.userDao().update(user)
            Log.d("userviewModel","data ${user.uid} ${user.name} ${user.phone} ${user.email} ${user.password}" )
            //Log.d("userviewModel","data RETURN ${data?.uid} ${data?.name} ${data?.phone} ${data?.email} ${data?.password}" )
        }
    }
}