package com.fenger.mineclearing

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fenger.mineclearing.data.GridItem
import com.fenger.mineclearing.data.GridState
import com.fenger.mineclearing.data.GridType

class GameViewModel : ViewModel() {
    var gameMatrix = MutableList(10) {
        MutableList(10) {
            mutableStateOf(GridItem(
                GridState.Close,
                listOf(GridType.Num, GridType.Mine).random()
            ))
        }
    }

    init {
        gameMatrix.forEachIndexed { indexI, arrayOfGridItems ->
            arrayOfGridItems.forEachIndexed { indexJ, gridItem ->
                if (gridItem.value.type == GridType.Num) {
                    val mineNum = allAroundMineNum(indexI, indexJ, gameMatrix)
                    gridItem.value.mineNum = mineNum
                }
            }
        }
    }

    private fun allAroundMineNum(i: Int, j: Int, grid: MutableList<MutableList<MutableState<GridItem>>>): Int {
        if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size) {
            return 1
        }
        return (around(i - 1, j, grid)
                + around(i + 1, j, grid)
                + around(i, j - 1, grid)
                + around(i, j + 1, grid)
                + around(i - 1, j - 1, grid)
                + around(i + 1, j + 1, grid)
                + around(i + 1, j - 1, grid)
                + around(i - 1, j + 1, grid))
    }

    private fun around(i: Int, j: Int, grid: MutableList<MutableList<MutableState<GridItem>>>): Int {
        if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size) {
            return 0
        }
        if (grid[i][j].value.type == GridType.Mine) {
            return 1
        }
        return 0
    }

    fun refreshState() {

    }
}
