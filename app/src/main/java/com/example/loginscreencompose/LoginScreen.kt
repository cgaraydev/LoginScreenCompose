package com.example.loginscreencompose

import android.util.Patterns
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navigationController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        LoginBody(navigationController, Modifier.align(Alignment.Center))
        LoginFooter(navigationController, Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun LoginBody(navigationController: NavHostController, modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(false) }
    val fontFamily = FontFamily(
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_medium, FontWeight.Medium)
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        MyTitle("Welcome,", fontFamily = fontFamily)
        MySubTitle("Glad to see you!", fontFamily = fontFamily)
        MySpacer(12.dp)
        Email(email, fontFamily = fontFamily) {
            email = it
            isLoginEnabled = enableLogin(email, password)
        }
        MySpacer(6.dp)
        Password(password, fontFamily = fontFamily) {
            password = it
            isLoginEnabled = enableLogin(email, password)
        }
        MySpacer(8.dp)
        ForgotPassword(
            "Forgot password?",
            modifier = Modifier.align(Alignment.End),
            fontFamily = fontFamily,
            navigationController
        )
        MySpacer(8.dp)
        MyLoginButton(isLoginEnabled, fontFamily)
        MySpacer(24.dp)
        MyLoginDivider(fontFamily)
        MySpacer(12.dp)
        MySocialButtons()
    }
}

@Composable
fun Email(email: String, fontFamily: FontFamily, onTextChanged: (String) -> Unit) {
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
fun Password(password: String, fontFamily: FontFamily, onTextChanged: (String) -> Unit) {
    val showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
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
fun MyTitle(text: String, fontFamily: FontFamily) {
    Text(
        text = text,
        fontSize = 28.sp,
        color = Color.White,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun MySubTitle(text: String, fontFamily: FontFamily) {
    Text(
        text = text,
        fontSize = 28.sp,
        color = Color.White,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun MySpacer(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}

@Composable
fun ForgotPassword(text: String, modifier: Modifier, fontFamily: FontFamily, navigationController: NavHostController) {
    Text(
        text = text,
        modifier = modifier.clickable { navigationController.navigate("forgotPasswordScreen") },
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Composable
fun MyLoginButton(isLoginEnabled: Boolean, fontFamily: FontFamily) {
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
            text = "Login",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun MyLoginDivider(fontFamily: FontFamily) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.White
        )
        Text(
            text = "Or Login with",
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
fun MySocialButtons() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "",
                modifier = Modifier.size(26.dp)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun LoginFooter(navigationController: NavHostController, modifier: Modifier) {
    val fontFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light)
    )
    Row(modifier = modifier) {
        Text(
            text = "Don't have an account?",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            color = Color.White,
            modifier = Modifier.padding(end = 6.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Sign Up now",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.clickable { navigationController.navigate("signUpScreen") }
        )
    }
}

fun enableLogin(email: String, password: String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6