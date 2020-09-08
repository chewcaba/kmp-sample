package com.chewcaba.lib

interface LoginInteractor {

    @Throws(BadLoginError::class)
    fun login(username: String, password: String): Result<Boolean>
}