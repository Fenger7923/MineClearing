package com.fenger.mineclearing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fenger.mineclearing.data.GridItem
import com.fenger.mineclearing.data.GridState
import com.fenger.mineclearing.ui.theme.MineClearingTheme
import com.fenger.mineclearing.ui.theme.MineTheme

/**
 * @author fengerzhang
 * @date 2021/12/14 15:10
 */
class GameActivity : ComponentActivity() {
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MineClearingTheme {
                GridPanel(viewModel.gameMatrix)
            }
        }
    }

    @Composable
    private fun GridPanel(matrix: MutableList<MutableList<MutableState<GridItem>>>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            matrix.forEach {
                GridLine(it)
            }
        }
    }

    @Composable
    private fun GridLine(numArray: MutableList<MutableState<GridItem>>) {
        Row(modifier = Modifier.fillMaxWidth()) {
            numArray.forEach { gridItem ->
                GridItemSpace(gridItem,
                    Modifier
                        .weight(1f)
                        .padding(1.dp)
                        .background(MaterialTheme.colors.onSurface)
                        .aspectRatio(1f)
                        .clickable {
                            gridItem.value.state = GridState.Open
                            viewModel.refreshState()
                        }
                )
            }
        }
    }

    @Composable
    private fun GridItemSpace(gridItem: MutableState<GridItem>, modifier: Modifier) {
        when (gridItem.value.state) {
            GridState.Close -> Box(
                modifier = modifier.background(MineTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {

            }
            GridState.Check -> Box(
                modifier = modifier.background(MineTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "123")
            }
            GridState.Open -> Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${gridItem.value.mineNum}")
            }
        }
    }
}