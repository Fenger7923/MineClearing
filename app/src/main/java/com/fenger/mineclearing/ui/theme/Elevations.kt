package com.fenger.mineclearing.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author fengerzhang
 * @date 2021/12/14 15:03
 */
@Immutable
data class Elevations(val card: Dp = 0.dp)

internal val LocalElevations = staticCompositionLocalOf { Elevations() }
