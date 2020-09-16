package com.chewcaba.lib

object LoginServiceFactory {
    fun makeInstance(): LoginService = FakeLoginService()
}

