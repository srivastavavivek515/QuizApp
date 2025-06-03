package com.example.altaquizz.home.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.example.altaquizz.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedDropdownMenu(headerText: String, menuItems: List<String>,text: String,onDropDownChange:(String)->Unit) {
    var expanded by remember { mutableStateOf(false) }
    // val allOptions = listOf("Kotlin", "Java", "Python", "C++", "Swift", "Go", "Rust")
   // var selectedOption by remember { mutableStateOf(menuItems[0]) }
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(headerText, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // TextField with search feature
            val containerColor = colorResource(id = R.color.headerColor)
            OutlinedTextField(
                value = text,
                onValueChange = {
                    expanded = !expanded
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = colorResource(id = R.color.iconColor)
                    )
                },
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    focusedTextColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    disabledTextColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(
                            id = R.color.headerColor
                        )
                    )
            ) {

                menuItems.forEachIndexed { index, s ->
                    DropdownMenuItem(text = { Text(text = s, color = Color.White) }, onClick = {
                        onDropDownChange(menuItems[index])
                        expanded = false
                    }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }
            }

        }
    }
}


@Composable
fun RoundedCornerButton(text:String,textColor:Color,containerColor: Color,onClick:()->Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        onClick = {
                  onClick()
        },
        shape = RoundedCornerShape(40.dp), // Set corner radius
        modifier = Modifier
            .width(200.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = textColor
        )

    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}


fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = " ")
    val alpha = transition.animateFloat(initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(animation = tween(durationMillis = 1000), repeatMode = RepeatMode.Reverse), label = "").value
        background(colorResource(id = R.color.gray_color).copy(alpha))
    
}

@Composable
fun prev(){
    QuizOption(optionName = "1", optionText = "3", selected = false, onOptionClick = { /*TODO*/ }) {
        
    }
}

@Composable
fun QuizOption(optionName:String,optionText:String,selected:Boolean,onOptionClick:()->Unit,onUnSelectOption:()->Unit) {
    val optionTextColor = if(selected) Color.White else Color.Black
    val transition = updateTransition(selected,"selected")
    val startColor by transition.animateColor(transitionSpec = { tween(durationMillis = 10, easing = LinearEasing) },
        label = "",) {
        if(it) colorResource(id = R.color.orange_color) else Color.White
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .height(60.dp)

        .clip(RoundedCornerShape(60.dp))
        .background(
            color = startColor
        )
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween){

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            onOptionClick()
        }) {

            if(!selected){

                Text(text = optionName, fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.White,modifier = Modifier
                    .size(40.dp)
                    .shadow(
                        10.dp, CircleShape, true,
                        colorResource(id = R.color.black)
                    )
                    .background(
                        color = colorResource(id = R.color.orange_color),
                        shape = CircleShape
                    )
                    .wrapContentSize())
            }

            Text(text = optionText, color = optionTextColor,fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 5.dp))
        }
        if(selected){
            Text(text = "X", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = colorResource(
                id = R.color.orange_color
            ),modifier = Modifier
                .size(40.dp)
                .shadow(
                    10.dp, CircleShape, true,
                    colorResource(id = R.color.black)
                )
                .background(color = colorResource(id = R.color.white), shape = CircleShape)

                .wrapContentSize()
                .clickable {
                    onUnSelectOption()
                })

        }
    }

}


@Composable
fun QuizOptionShimmer(optionNo:String,options:String,selected:Boolean,onOptionClick:()->Unit,onUnSelectOption:()->Unit) {
    val optionTextColor = if(selected) Color.White else Color.Black
    val transition = updateTransition(selected,"selected")
    val startColor by transition.animateColor(transitionSpec = { tween(durationMillis = 10, easing = LinearEasing) },
        label = "",) {
        if(it) colorResource(id = R.color.orange_color) else Color.White
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .height(60.dp)

        .clip(RoundedCornerShape(60.dp))
        .padding(10.dp).shimmerEffect()){

    }

}



