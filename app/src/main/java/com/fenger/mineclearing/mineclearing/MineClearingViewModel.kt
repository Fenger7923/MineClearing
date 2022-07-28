package com.fenger.mineclearing.mineclearing

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fenger.mineclearing.gradeOfDifficulty
import com.fenger.mineclearing.verBlockNum

/**
 * @author fengerzhang
 * @date 2022/2/24 16:43
 */
class MineClearingViewModel: ViewModel() {

    // 初始化所有的雷区
    val mines: MutableList<Array<Int>> = mutableListOf<Array<Int>>().also {
        Log.i("MineClearingViewModel", "There are less than ${verBlockNum * 2} mines")
        for (item in 0 until verBlockNum * 2) {
            it.add(arrayOf((0..gradeOfDifficulty).random(), (0..verBlockNum).random())) // 随机雷数量
        }
    }
}