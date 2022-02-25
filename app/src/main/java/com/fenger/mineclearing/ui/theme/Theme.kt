package com.fenger.mineclearing.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun MineClearingTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = ColorPalette, typography = Typography, content = content)
}

object MineTheme {
    val colors: Colors
        @Composable get() = MaterialTheme.colors

    val typography: Typography
        @Composable get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable get() = MaterialTheme.shapes

    val elevations: Elevations
        @Composable get() = LocalElevations.current
}