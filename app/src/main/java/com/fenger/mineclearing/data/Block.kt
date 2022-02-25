package com.fenger.mineclearing.data

/**
 * @author fengerzhang
 * @date 2022/2/24 16:36
 *
 * @param type 方格里内容: 地雷（Mine）或 数字(Num)
 * @param state 方格的状态：开(Open)或者关(Close)
 * @param aroundMine 附近雷数
 */
data class Block(val type: BlockType, var state: BlockState, var aroundMine: Int): Cloneable {
    fun isOpen(): Boolean {
        return state == BlockState.Open
    }
}

enum class BlockType {
    Mine,
    Num
}

enum class BlockState {
    Open,
    Close
}