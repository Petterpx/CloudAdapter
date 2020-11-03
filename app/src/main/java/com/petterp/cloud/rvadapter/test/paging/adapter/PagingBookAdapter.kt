package com.petterp.cloud.rvadapter.test.paging.adapter

import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.base.BaseViewHolder
import com.petterp.cloud.rvadapter.test.paging.PokemonEntity
import com.petterp.paging.source.base.BasePageAdapter

/**
 * @Author petterp
 * @Date 2020/10/28-6:12 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class PagingBookAdapter :
    BasePageAdapter<PokemonEntity, BaseViewHolder>(PokemonEntity.diff, R.layout.item_android) {

    override fun convert(holder: BaseViewHolder, item: PokemonEntity) {
        holder.setText(R.id.tvAndroidTitle, item.title)

    }
}