package com.chewcaba.lib

interface LoginInteractorFactory {
    fun LoginInteractorFactory.makeLoginInteractor(): LoginInteractor = FakeLoginInteractor()
}

