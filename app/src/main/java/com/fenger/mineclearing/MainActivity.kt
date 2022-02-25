package com.fenger.mineclearing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fenger.mineclearing.mineclearing.MineClearingActivity
import com.fenger.mineclearing.ui.theme.MineClearingTheme
import com.fenger.mineclearing.ui.theme.MineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MineClearingTheme {
                MainApp()
            }
        }
    }

    @Composable
    private fun MainApp() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val intent = Intent().apply {
                        setClass(this@MainActivity, MineClearingActivity::class.java)
                    }
                    startActivity(intent)
                },
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = MineTheme.colors.background,
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(text = "开始游戏")
            }

            Button(
                onClick = {
                    // TODO 增加游戏难度设置
                },
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = MineTheme.colors.background,
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(text = "游戏设置")
            }
        }
    }
}