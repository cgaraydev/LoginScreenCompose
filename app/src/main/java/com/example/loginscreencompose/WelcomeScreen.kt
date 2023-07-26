package com.example.loginscreencompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navigationController: NavHostController) {
    Box(
        Modifier.fillMaxSize()
    ) {
        WelcomeBody(navigationController, Modifier.align(Alignment.Center))
    }
}

@Composable
fun WelcomeBody(navigationController: NavHostController, modifier: Modifier) {
    val fontFamily = FontFamily(
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_medium, FontWeight.Medium)
    )
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.default_monochrome_black),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        MySpacer(16.dp)
        Button(
            onClick = { navigationController.navigate("loginScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Login",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        MySpacer(12.dp)
        Button(
            onClick = { navigationController.navigate("signUpScreen") },
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text(
                text = "Sign Up",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}