package com.petterp.paging.source.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.LoadState
import com.petterp.cloud.rvadapter.base.BaseQuickAdapter
import com.petterp.cloud.rvadapter.base.BaseViewHolder

/**
 * @Author petterp
 * @Date 2020/10/27-7:17 PM
 * @Email ShiyihuiCloud@163.com
 * @Function BaseLoadStateAdapter
 * paging-> 带状态的adapter
 * doc: 这里指的状态是，paging在加载时的状态，比如通过 footer 或者header 显示出来，给用户一个好的提示
 * 本质的实现依然是一个adapter适配器，不过google加入了 [loadState] 可以实时获取当前加载状态
 *
 */
abstract class BaseLoadStateAdapter<T : Any, VH : BaseViewHolder>(
    @LayoutRes private val layoutResId: Int, data: MutableList<T>? = null
) : BaseQuickAdapter<T, VH>(layoutResId, data) {
    /**
     * LoadState to present in the adapter.
     *
     * Changing this property will immediately notify the Adapter to change the item it's
     * presenting.
     */
    var loadState: LoadState = LoadState.NotLoading(endOfPaginationReached = false)
        set(loadState) {
            if (field != loadState) {
                val oldItem = displayLoadStateAsItem(field)
                val newItem = displayLoadStateAsItem(loadState)

                if (oldItem && !newItem) {
                    notifyItemRemoved(0)
                } else if (newItem && !oldItem) {
                    notifyItemInserted(0)
                } else if (oldItem && newItem) {
                    notifyItemChanged(0)
                }
                field = loadState
            }
        }

    override fun convert(holder: VH, item: T) {
        convert(holder, item, loadState)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateDefViewHolder(parent, viewType, loadState)
    }

    /**
     * Override this method and return your ViewHolder.
     * 重写此方法，返回你的ViewHolder。 适用于paging
     */
    protected fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int,
        loadState: LoadState
    ): VH {
        return super.onCreateDefViewHolder(parent, viewType)
    }


    final override fun getItemCount(): Int = if (displayLoadStateAsItem(loadState)) 1 else 0


    abstract fun convert(holder: VH, item: T, loadState: LoadState)

    /**
     * Returns true if the LoadState should be displayed as a list item when active.
     *
     * By default, [LoadState.Loading] and [LoadState.Error] present as list items, others do not.
     */
    open fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error
    }

}
