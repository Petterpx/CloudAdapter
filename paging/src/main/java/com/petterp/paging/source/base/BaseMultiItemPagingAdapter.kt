package com.petterp.paging.source.base

import androidx.recyclerview.widget.DiffUtil
import com.petterp.cloud.rvadapter.base.BaseViewHolder
import com.petterp.cloud.rvadapter.expand.IMultiItemExpand
import com.petterp.cloud.rvadapter.entry.MultiItemEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @Author petterp
 * @Date 2020/10/28-10:58 AM
 * @Email ShiyihuiCloud@163.com
 * @Function 通用
 * [IMultiItemExpand] 多item扩展器
 */
abstract class BaseMultiItemPagingAdapter<T : MultiItemEntity, VH : BaseViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    workerDispatcher: CoroutineDispatcher = Dispatchers.Default,
) :
    BasePageAdapter<T, VH>(
        diffCallback,
        0,
        mainDispatcher = mainDispatcher,
        workerDispatcher = workerDispatcher
    ), IMultiItemExpand {

    override fun getItemViewType(position: Int): Int {
        return spareItemTypes[getItem(position).itemLayout]
    }
}