package com.example.iubappjg.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iubappjg.data.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)

abstract class AppDataBase:RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase?=null

        fun getInstance(context:Context):AppDataBase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    name = "ra_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}
