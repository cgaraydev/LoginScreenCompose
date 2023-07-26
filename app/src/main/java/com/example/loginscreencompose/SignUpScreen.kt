package com.example.loginscreencompose

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navigationController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        SignUpBody(Modifier.align(Alignment.Center))
        SignUpFooter(navigationController, Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun SignUpFooter(navigationController: NavHostController, modifier: Modifier) {
    val fontFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light)
    )
    Row(modifier = modifier) {
        Text(
            text = "Already have an account?",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            color = Color.White,
            modifier = Modifier.padding(end = 6.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Login now",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.clickable { navigationController.navigate("loginScreen") }
        )
    }
}

@Composable
fun SignUpBody(modifier: Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isSignUpEnabled by remember { mutableStateOf(false) }
    val fontFamily = FontFamily(
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_regular, FontWeight.Normal)
    )
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SignUpTitle(fontFamily)
        MySpacer(20.dp)
        FullName(name, fontFamily) {
            name = it
            isSignUpEnabled = enableSignUp(name, email, password, confirmPassword)
        }
        MySpacer(6.dp)
        SignUpEmail(email, fontFamily) {
            email = it
            isSignUpEnabled = enableSignUp(name, email, password, confirmPassword)
        }
        MySpacer(6.dp)
        SignUpPassword(password, fontFamily) {
            password = it
            isSignUpEnabled = enableSignUp(name, email, password, confirmPassword)
        }
        MySpacer(6.dp)
        ConfirmPassword(confirmPassword, fontFamily) {
            confirmPassword = it
            isSignUpEnabled = enableSignUp(name, email, password, confirmPassword)
        }
        MySpacer(12.dp)
        SignUpButton(isSignUpEnabled, fontFamily)
        MySpacer(20.dp)
        MySignUpDivider(fontFamily)
        MySpacer(12.dp)
        MySocialButtons()
    }
}


@Composable
fun MySignUpDivider(fontFamily: FontFamily) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.White
        )
        Text(
            text = "Or Sign Up with",
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        )
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.White
        )
    }

}

@Composable
fun SignUpButton(isSignUpEnabled: Boolean, fontFamily: FontFamily) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = isSignUpEnabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            disabledContentColor = Color(0xFF6D6A6A)
        )
    ) {
        Text(
            text = "Sign Up",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ConfirmPassword(
    confirmPassword: String,
    fontFamily: FontFamily,
    onTextChanged: (String) -> Unit
) {
    val showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(
                text = "Confirm Password",
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
        ),
//        trailingIcon = {
//            val image = if (showPassword) {
//                Icons.Filled.VisibilityOff
//            } else {
//                Icons.Filled.Visibility
//            }
//            IconButton(onClick = { showPassword = !showPassword }) {
//                Icon(imageVector = image, contentDescription = "")
//            }
//        },
        visualTransformation = if (showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
fun SignUpPassword(
    confirmPassword: String,
    fontFamily: FontFamily,
    onTextChanged: (String) -> Unit
) {
    val showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(
                text = "Password",
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
        ),
//        trailingIcon = {
//            val image = if (showPassword) {
//                Icons.Filled.VisibilityOff
//            } else {
//                Icons.Filled.Visibility
//            }
//            IconButton(onClick = { showPassword = !showPassword }) {
//                Icon(imageVector = image, contentDescription = "")
//            }
//        },
        visualTransformation = if (showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
fun SignUpEmail(email: String, fontFamily: FontFamily, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
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
}


@Composable
fun FullName(name: String, fontFamily: FontFamily, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
//        label = { Text(text = Name)},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        placeholder = {
            Text(
                text = "Name",
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
}

@Composable
fun SignUpTitle(fontFamily: FontFamily) {
    Text(
        text = "Create Account",
        fontSize = 28.sp,
        color = Color.White,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )
}

fun enableSignUp(name: String, email: String, password: String, confirmPassword: String) =
    name.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email)
        .matches() && password.length > 6 && confirmPassword.length > 6 && password == confirmPassword