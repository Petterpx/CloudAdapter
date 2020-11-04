package com.cloud.adapter.ui.adapter

import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.base.BaseViewHolder
import com.cloud.adapter.data.WanEntity
import com.cloud.paging.base.BasePageAdapter

/**
 * @Author petterp
 * @Date 2020/10/28-6:12 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class PagingWanAdapter :
    BasePageAdapter<WanEntity, BaseViewHolder>(WanEntity.diff, R.layout.item_android) {

    override fun convert(holder: BaseViewHolder, item: WanEntity) {
        holder.setText(R.id.tvAndroidTitle, item.title)
    }
}