package com.example.loginscreencompose

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPasswordScreen() {
    Box(Modifier.fillMaxSize()) {
        ForgotPasswordBody(Modifier.align(Alignment.Center))
    }
}

@Composable
fun ForgotPasswordBody(modifier: Modifier) {
    val fontFamily = FontFamily(
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_regular, FontWeight.Normal)
    )
    var email by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(
            text = "Forgot Password?",
            fontSize = 32.sp,
            color = Color.White,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
        )
        MySpacer(20.dp)
        Text(
            text = "Don't worry!. Please enter your email address.",
            fontSize = 14.sp,
            color = Color.White,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        )
        MySpacer(32.dp)
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isLoginEnabled = enableSubmit(email)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(
                    text = "Email",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color(0xFFFFFFFF),
                placeholderColor = Color(0xFFC0B7B7),
                unfocusedBorderColor = Color(0xFFC0B7B7)
            )
        )
        MySpacer(28.dp)
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = isLoginEnabled,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                disabledContentColor = Color(0xFF6D6A6A)
            )
        ) {
            Text(
                text = "Submit",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

fun enableSubmit(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()