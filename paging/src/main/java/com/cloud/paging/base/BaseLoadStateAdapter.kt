package com.cloud.paging.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.cloud.adapter.base.BaseViewHolder
import com.cloud.adapter.expand.IAdapterExt
import com.cloud.adapter.expand.getItemView

/**
 * @Author petterp
 * @Date 2020/10/27-7:17 PM
 * @Email ShiyihuiCloud@163.com
 * @Function BaseLoadStateAdapter
 * paging-> 带状态的adapter
 * doc: 这里指的状态是，paging在加载时的状态，比如通过 footer 或者header 显示出来，给用户一个好的提示
 * 本质的实现依然是一个adapter适配器，不过google加入了 [loadState] 可以实时获取当前加载状态
 */
abstract class BaseLoadStateAdapter<VH : BaseViewHolder>(
    @LayoutRes private val layoutResId: Int
) : LoadStateAdapter<VH>(), IAdapterExt {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): VH {
        return createBaseViewHolder(this, parent.getItemView(layoutResId))
    }

    override fun onBindViewHolder(holder: VH, loadState: LoadState) {
        convert(holder, loadState)
    }


    abstract fun convert(holder: VH, loadState: LoadState)
}

