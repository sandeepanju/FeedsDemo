package com.example.feeddemo.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users",indices = [Index(value = ["emailId","lastName","firstName"], unique = true)])
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    val emailId: String,
    val lastName: String="",
    val imageUrl: String="",
    val firstName: String=""
)