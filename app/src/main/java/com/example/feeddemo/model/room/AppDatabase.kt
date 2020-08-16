package com.example.feeddemo.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feeddemo.model.dao.UserDao
import com.example.feeddemo.model.entity.User


@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}