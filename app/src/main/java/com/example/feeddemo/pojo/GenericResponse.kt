package com.example.feeddemo.pojo

data class GenericResponse<T>(val status:Boolean, val items:T, val message: String? = null)