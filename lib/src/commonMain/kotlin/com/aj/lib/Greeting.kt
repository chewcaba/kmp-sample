package com.aj.lib

class Greeting {
    fun message(): String = "Welcome ${Device().platformName()} User, Please Login"
}