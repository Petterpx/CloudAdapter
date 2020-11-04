package com.cloud.adapter.ui.adapter

import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.base.BaseQuickAdapter
import com.cloud.adapter.base.BaseViewHolder

/**
 * @Author petterp
 * @Date 2020/10/28-5:57 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class BookAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_android, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvAndroidTitle, item)
    }
}