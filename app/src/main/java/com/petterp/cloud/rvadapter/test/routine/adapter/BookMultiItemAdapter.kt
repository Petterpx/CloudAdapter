package com.petterp.cloud.rvadapter.test.routine.adapter

import androidx.annotation.DrawableRes
import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.base.BaseMultiItemAdapter
import com.petterp.cloud.rvadapter.base.BaseViewHolder
import com.petterp.cloud.rvadapter.entry.MultiItemEntity

/**
 * @Author petterp
 * @Date 2020/10/28-2:59 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */


sealed class BookModel : MultiItemEntity {
    //种类-带图
    data class ImageToText(
        val name: String,
        @DrawableRes val image: Int,
        override val itemLayout: Int=R.layout.item_guard
    ) :
        BookModel()

    //种类-单文字
    data class SingleText(
        val name: String,
        override val itemLayout: Int = R.layout.item_android
    ) : BookModel()
}

class BookMultiItemAdapter(data: MutableList<BookModel>) :
    BaseMultiItemAdapter<BookModel, BaseViewHolder>(data) {

    override fun convert(holder: BaseViewHolder, item: BookModel) {
        when (item) {
            is BookModel.ImageToText -> {
                holder.setText(R.id.tvTitle, item.name)
                holder.setImageResource(R.id.ivLeft, item.image)
            }

            is BookModel.SingleText -> {
                holder.setText(R.id.tvAndroidTitle, item.name)
            }

        }
    }

}