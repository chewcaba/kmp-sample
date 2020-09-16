package com.aj.kmpsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.chewcaba.lib.LoginService
import com.chewcaba.lib.Result

class MainActivity : AppCompatActivity() {

    private val usernameState = mutableStateOf("")
    private val passwordState = mutableStateOf("")
    private val dialogState = mutableStateOf(false)
    private val loginService = LoginService.Factory.makeInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            MaterialTheme {
                LoginScreen(usernameState, passwordState) {
                    dialogState.value = true
                }
                if (dialogState.value) {
                    LoginDialog()
                }
            }
        }
    }

    @Composable
    private fun LoginDialog() {
        val result = when (val login = loginService.login(usernameState.value, passwordState.value)) {
            is Result.Success -> login.data
            is Result.Error -> false
        }

        if (dialogState.value) {
            AlertDialog(
                onDismissRequest = {
                    dialogState.value = false
                },
                title = {
                    Text(text = "Alert")
                },
                text = {
                    Text(text = "Was login successful? $result")
                },
                buttons = {
                    Button(
                        onClick = {
                            dialogState.value = false
                        },
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        backgroundColor = MaterialTheme.colors.secondary,
                        elevation = 5.dp
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            )
        }
    }
}