package com.aj.kmpsample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    usernameState: MutableState<String>,
    passwordState: MutableState<String>,
    onClick: () -> Unit
) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalGravity = Alignment.CenterHorizontally,
        ) {
            Header("Welcome, Please Login")
            EditText(hint = "Username", state = usernameState)
            EditText(hint = "Password", state = passwordState)
            CustomButton(text = "Login", onClick = onClick)
        }
    }
}

@Composable
fun Header(header: String) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = header,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EditText(hint: String, state: MutableState<String>) {
    Row(modifier = Modifier.padding(10.dp)) {
        TextField(
            value = state.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { state.value = it },
            backgroundColor = MaterialTheme.colors.primary,
            activeColor = Color.White,
            inactiveColor = Color.White,
            label = {
                Text(hint)
            }
        )
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(10.dp)) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.secondary,
            elevation = 5.dp
        ) {
            Text(text, color = Color.White)
        }
    }
}