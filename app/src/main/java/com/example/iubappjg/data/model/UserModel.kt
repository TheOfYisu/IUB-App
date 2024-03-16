package com.example.iubappjg.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="user")
data class UserModel (
    @PrimaryKey(autoGenerate = true)
    val uid:Long?=null,

    @ColumnInfo(name ="email")
    val email:String,

    @ColumnInfo(name ="password")
    val password:String,

    @ColumnInfo(name ="name")
    val name:String,

    @ColumnInfo(name ="phone")
    val phone:String,
)