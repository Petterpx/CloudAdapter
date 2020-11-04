package com.cloud.adapter.ui.adapter

import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.base.BaseMultiItemAdapter
import com.cloud.adapter.base.BaseViewHolder
import com.cloud.adapter.data.model.WanModel

/**
 * @Author petterp
 * @Date 2020/10/28-2:59 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 多类型adater
 */
class BookMultiItemAdapter(data: MutableList<WanModel>) :
    BaseMultiItemAdapter<WanModel, BaseViewHolder>(data) {

    override fun convert(holder: BaseViewHolder, item: WanModel) {
        when (item) {
            is WanModel.ImageToText -> {
                holder.setText(R.id.tvTitle, item.name)
                holder.setImageResource(R.id.ivLeft, item.image)
            }

            is WanModel.SingleText -> {
                holder.setText(R.id.tvAndroidTitle, item.name)
            }

        }
    }

}