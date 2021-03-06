package com.aj.lib

internal class FakeLoginService : LoginService {

    @Throws(BadLoginError::class)
    override fun login(username: String, password: String): Result<Boolean> {
        val isUsernameMatch = username == "user"
        val isPasswordMatch = password == "password"

        if (username == "baduser") {
            return Result.Error(BadLoginError())
        }

        return when {
            isUsernameMatch && isPasswordMatch -> Result.Success(true)
            else -> Result.Success(false)
        }
    }
}