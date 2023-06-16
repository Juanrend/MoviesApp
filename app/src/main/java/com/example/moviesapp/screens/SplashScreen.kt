package com.example.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesapp.R
import com.example.moviesapp.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        navController.navigate(AppScreens.LoginScreen.route)
    }


    Splash()
}


@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Black
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.iconmoviesapp),
            contentDescription = "",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Movie App",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    Splash()

}