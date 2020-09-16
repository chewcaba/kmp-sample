package com.chewcaba.lib

interface LoginService {

    @Throws(BadLoginError::class)
    fun login(username: String, password: String): Result<Boolean>

    companion object Factory {
        fun makeInstance(): LoginService = FakeLoginService()
    }
}