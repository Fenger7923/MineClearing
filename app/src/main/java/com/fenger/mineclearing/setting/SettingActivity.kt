package com.fenger.mineclearing.setting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fenger.mineclearing.gradeOfDifficulty
import kotlin.math.ceil

/**
 * @author fengerzhang
 * @date 2022/7/28 11:42
 */
class SettingActivity : ComponentActivity() {


    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,

            ) {
                DifficultySetting()
            }
        }
    }

    @Composable
    private fun DifficultySetting() {
        var grade by remember {
            mutableStateOf(gradeOfDifficulty.toFloat())
        }
        Text(text = "难度等级: $gradeOfDifficulty")
        Slider(
            value = grade,
            colors = SliderDefaults.colors(
                thumbColor = Color.White, // 圆圈的颜色
                activeTrackColor = Color(0xFF0079D3)
            ),
            valueRange = 10f..15f,
            onValueChange = {
                gradeOfDifficulty = ceil(it).toInt()
                grade = it

            })
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "10")
            Text(text = "15")
        }
    }
}