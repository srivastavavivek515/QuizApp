package com.example.altaquizz.components

import android.R.attr.padding
import android.R.attr.text
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
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
fun RoundedCornerButton(modifier: Modifier,text:String,textColor:Color,containerColor: Color,onClick:()->Unit) {
    Spacer(modifier = Modifier
        .height(20.dp)
        .background(Color.Red))
    Button(
        onClick = {
                  onClick()
        },
        shape = RoundedCornerShape(40.dp), // Set corner radius
        modifier = modifier
            .height(50.dp),
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
        ).clickable {
            if (selected) onUnSelectOption() else onOptionClick()
        }.padding(10.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween){

        Row(verticalAlignment = Alignment.CenterVertically) {

                Text(text = optionName, style = TextStyle(lineHeight = 1.sp),fontWeight = FontWeight.Bold, fontSize = 30.sp, color = colorResource(if (selected) R.color.orange_color else R.color.white), modifier = Modifier
                    .size(40.dp)
                    .shadow(
                        10.dp, CircleShape, true,
                        colorResource(id = R.color.black)
                    )
                    .background(
                        color = colorResource(id = if(selected) R.color.white else R.color.orange_color),
                        shape = CircleShape
                    )
                    .wrapContentSize())

            Text(text = optionText, color = optionTextColor,fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 5.dp))
        }
      /*  if(selected){
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

        }*/
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
        .padding(10.dp)
        .shimmerEffect()){

    }
}

@Composable
fun Int.toDp():Dp{
  return with(LocalDensity.current) { this@toDp.toDp() }
}

@Preview
@Composable
fun showPrev(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        drawRing("10")
    }
}


@Composable
fun drawRing(percent:String) {
    val startAngle =-90f
    val radius = 250
    val strokeWidth = 20
    val arcSize = Size((radius*2 - strokeWidth).toFloat() , (radius * 2- strokeWidth).toFloat())

    Box (modifier = Modifier.width(arcSize.width.toInt().toDp()).height(arcSize.height.toInt().toDp()), contentAlignment = Alignment.Center){
        Canvas(modifier = Modifier.fillMaxSize()){
            drawArc(size = arcSize,color = Color.White, useCenter = false,  startAngle = 0f, sweepAngle = 360f, style = Stroke(strokeWidth.toFloat(), cap = StrokeCap.Butt))
            drawArc( size = arcSize,color = Color.Green, useCenter = false, startAngle = startAngle, sweepAngle = 36f, style = Stroke(strokeWidth.toFloat(), cap = StrokeCap.Butt))
        }
        Column {
            Text("${percent}/100", color = Color.Green, fontWeight = FontWeight.Bold)
        }
    }

}




