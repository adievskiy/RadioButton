package com.example.radiobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radiobutton.ui.theme.Brown

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val carsColor = listOf(
                CarColor(Color.Black, R.drawable.car_black),
                CarColor(Brown, R.drawable.car_brown),
                CarColor(Color.Gray, R.drawable.car_gray),
                CarColor(Color.LightGray, R.drawable.car_light_gray),
                CarColor(Color.White, R.drawable.car_white)
            )
            val carsClass = listOf(
                CarClass("Эконом", "1 239 900"),
                CarClass("Комфорт", "1 525 000"),
                CarClass("Люкс", "1 626 000")
            )
            val (selectedColor, onColorSelected) = remember { mutableStateOf(carsColor[0]) }
            val (selectedClass, onClassSelected) = remember { mutableStateOf(carsClass[0]) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(top = 45.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = painterResource(selectedColor.image),
                    contentDescription = null,
                    Modifier.size(width = 350.dp, height = 210.dp)
                )

                Text(
                    text = "Выберите цвет:",
                    fontSize = 24.sp
                )

                Row(Modifier.selectableGroup()) {
                    carsColor.forEach { color ->
                        RadioButton(
                            selected = (color == selectedColor),
                            onClick = { onColorSelected(color) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = color.color,
                                unselectedColor = color.color
                            )
                        )
                    }
                }
                Spacer(Modifier.padding(10.dp))
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.LightGray)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "Выберите комплектацию:",
                        fontSize = 22.sp
                    )
                    carsClass.forEach { carClass ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = (carClass == selectedClass),
                                onClick = { onClassSelected(carClass) },
                            )
                            Text(
                                text = carClass.name,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
                Spacer(Modifier.padding(10.dp))
                Text(
                    text = "${selectedClass.price} рублей",
                    fontSize = 28.sp
                )
            }
        }
    }
}

data class CarColor(val color: Color, val image: Int)
data class CarClass(val name: String, val price: String)