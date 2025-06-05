package com.example.altaquizz.main_activity

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import com.example.altaquizz.R
import com.example.altaquizz.navigation.*
import com.example.altaquizz.ui.theme.*
import com.google.accompanist.systemuicontroller.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AltaQuizzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    changeStatusBarColor()
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.black))
                    ) {
                       setUpNavGraph()
                    }
                }
            }
        }
    }
}

@Composable
fun changeStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFF393E46)
        )
    }


}

