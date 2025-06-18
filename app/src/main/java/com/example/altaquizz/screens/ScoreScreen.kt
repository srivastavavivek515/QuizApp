package com.example.altaquizz.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import com.example.altaquizz.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.altaquizz.components.RoundedCornerButton
import com.example.altaquizz.components.DrawRing

/**
 * @author Altametrics Inc. Created On 16/06/25 2:49 pm
 */

@Composable
fun ScoreScreen(navHostController: NavHostController,score:Int){
    BackHandler {
        navHostController.popBackStack()
        navHostController.popBackStack()
    }
    Column(modifier = Modifier.fillMaxSize().background(color = colorResource(id = R.color.headerColor)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(30.dp))
      Image(contentDescription = "", painter = painterResource(id = R.drawable.victory_cup), modifier = Modifier.width(200.dp).height(200.dp))
        Spacer(modifier = Modifier.height(30.dp))
        Text("Congratulations!!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Keep trying and you will get the 100 % score one day", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(30.dp))
        DrawRing("$score")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCornerButton(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), text = "Play Again", textColor = colorResource(id = R.color.black), containerColor = Color.White) {
            navHostController.popBackStack()
            navHostController.popBackStack()
        }
    }
}