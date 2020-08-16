package com.example.feeddemo.model.repository

import com.example.feeddemo.model.api.UserApi
import com.example.feeddemo.model.dao.UserDao
import com.example.feeddemo.pojo.RequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


open class UserRepository(private val userApi: UserApi, private val userDao: UserDao) {

    var data = userDao.findAll()

    suspend fun refreshUserList(requestModel: RequestModel) {
        withContext(Dispatchers.IO) {
            val users = userApi.getAllAsync(requestModel)
            users.body()?.items?.let { userDao.add(it) }
        }
    }
}