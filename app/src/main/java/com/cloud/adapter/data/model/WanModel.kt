package com.cloud.adapter.data.model

import androidx.annotation.DrawableRes
import com.cloud.adapter.entry.MultiItemEntity
import com.cloud.adapter.rvadapter.R

/**
 * @Author petterp
 * @Date 2020/11/4-3:56 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 数据Model
 */
sealed class WanModel : MultiItemEntity {
    //种类-带图
    data class ImageToText(
        val name: String,
        @DrawableRes val image: Int,
        override val itemLayout: Int = R.layout.item_guard
    ) :
        WanModel()

    //种类-单文字
    data class SingleText(
        val name: String,
        override val itemLayout: Int = R.layout.item_android
    ) : WanModel()
}