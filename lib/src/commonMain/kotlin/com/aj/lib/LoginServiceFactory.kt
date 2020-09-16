package com.aj.lib

object LoginServiceFactory {
    fun makeInstance(): LoginService = FakeLoginService()
}

