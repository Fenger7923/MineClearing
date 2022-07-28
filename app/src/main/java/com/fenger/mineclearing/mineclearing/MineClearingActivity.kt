package com.fenger.mineclearing.mineclearing

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.fenger.mineclearing.data.Block
import com.fenger.mineclearing.data.BlockState
import com.fenger.mineclearing.data.BlockType
import com.fenger.mineclearing.gradeOfDifficulty
import com.fenger.mineclearing.ui.theme.MineClearingTheme
import com.fenger.mineclearing.verBlockNum
import kotlin.math.ceil

/**
 * @author fengerzhang
 * @date 2022/2/24 16:41
 */
class MineClearingActivity : ComponentActivity() {
    private val viewModel by viewModels<MineClearingViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MineClearingTheme {
                MineBlocks()
            }
        }
    }

    @Composable
    private fun MineBlocks() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 纵向格子数不填满，所以留下3个格子
            // 这里不做异形屏适配，都默认宽小于高
            verBlockNum =
                ceil(LocalConfiguration.current.screenHeightDp.dp /
                        LocalConfiguration.current.screenWidthDp.dp * gradeOfDifficulty).toInt() - 3
            for (x in 0 until verBlockNum) {
                Row {
                    for (y in 0 until gradeOfDifficulty) {
                        var block by remember {
                            mutableStateOf(
                                Block(
                                    if (isInList(
                                            viewModel.mines,
                                            arrayOf(x, y)
                                        )
                                    ) BlockType.Mine else BlockType.Num,
                                    BlockState.Close,
                                    getAroundMinesNum(x, y)
                                ), object : SnapshotMutationPolicy<Block> {
                                    override fun equivalent(a: Block, b: Block): Boolean {
                                        return false
                                    }
                                })
                        }

                        BlockItem(block = block) {
                            if (block.type == BlockType.Mine) {
                                Toast.makeText(applicationContext, "Lose!", Toast.LENGTH_SHORT)
                                    .show()
                                return@BlockItem
                            }
                            block = block.copy(state = BlockState.Open)
                        }
                    }
                }
            }
        }
    }

    // 每一个block
    @Composable
    fun BlockItem(block: Block, onClickListener: () -> Unit) {
        // 这里不做异形屏适配，都默认宽小于高
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val blockWidth = screenWidth / gradeOfDifficulty
        Box(
            modifier = Modifier
                .width(blockWidth)
                .height(blockWidth)
                .border(1.dp, color = Color.Black)
                .background(color = if (block.isOpen()) Color.Gray else Color.White)
                .clickable { onClickListener.invoke() }
        ) {
            if (block.state == BlockState.Open) {
                Text(
                    text = block.aroundMine.toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    // 这一段计数代码有待优化 计算每个空格应该显示的数字
    private fun getAroundMinesNum(positionX: Int, positionY: Int): Int {
        var mines = 0
        if (positionX - 1 >= 0 && positionY - 1 >= 0 && isInList(
                viewModel.mines,
                arrayOf(positionX - 1, positionY - 1)
            )
        ) {
            mines++
        }
        if (positionX - 1 >= 0 && positionY + 1 < 10 && isInList(
                viewModel.mines,
                arrayOf(positionX - 1, positionY + 1)
            )
        ) {
            mines++
        }
        if (positionX - 1 >= 0 && isInList(viewModel.mines, arrayOf(positionX - 1, positionY))) {
            mines++
        }
        if (positionY - 1 >= 0 && isInList(viewModel.mines, arrayOf(positionX, positionY - 1))) {
            mines++
        }
        if (positionY + 1 < 10 && isInList(viewModel.mines, arrayOf(positionX, positionY + 1))) {
            mines++
        }
        if (positionX + 1 < 10 && positionY - 1 >= 0 && isInList(
                viewModel.mines,
                arrayOf(positionX + 1, positionY - 1)
            )
        ) {
            mines++
        }
        if (positionX + 1 < 10 && positionY + 1 < 10 && isInList(
                viewModel.mines,
                arrayOf(positionX + 1, positionY + 1)
            )
        ) {
            mines++
        }
        if (positionX + 1 < 10 && isInList(viewModel.mines, arrayOf(positionX + 1, positionY))) {
            mines++
        }
        return mines
    }

    // 所选block是否存在雷
    private fun isInList(a: List<Array<Int>>, b: Array<Int>): Boolean {
        for (item in a) {
            if (item.contentEquals(b)) {
                return true
            }
        }
        return false
    }
}



