package com.fenger.mineclearing.mineclearing

import androidx.lifecycle.ViewModel

/**
 * @author fengerzhang
 * @date 2022/2/24 16:43
 */
class MineClearingViewModel: ViewModel() {
    // 初始化所有的雷区
    val mines: MutableList<Array<Int>> = mutableListOf<Array<Int>>().also {
        for (item in 0 until 10) {
            it.add(arrayOf((0..10).random(), (0..10).random()))
        }
    }


}