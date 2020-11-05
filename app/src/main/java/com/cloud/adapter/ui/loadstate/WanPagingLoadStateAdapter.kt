package com.cloud.adapter.ui.loadstate

import android.util.Log
import androidx.paging.LoadState
import com.cloud.adapter.base.BaseViewHolder
import com.cloud.adapter.rvadapter.R
import com.cloud.paging.base.BaseLoadStateAdapter
import com.cloud.paging.base.BaseLoadViewHolder

/**
 * @Author petterp
 * @Date 2020/11/4-4:28 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 下拉加载更多Footer
 */
class WanPagingLoadStateAdapter(private val obj: () -> Unit) :
    BaseLoadStateAdapter<BaseLoadViewHolder>(R.layout.item_load_footer) {

    override fun convert(holder: BaseLoadViewHolder, loadState: LoadState) {
        Log.e("petterpx", "当前状态:$loadState")
        //处理错误
        if (loadState is LoadState.Error) {
            holder.setVisible(R.id.progBar, true)
        }

        //加载中
        if (loadState is LoadState.Loading) {
            holder.setVisible(R.id.progBar, true)
        }

        //空闲状态
        if (loadState is LoadState.NotLoading) {
            holder.setGone(R.id.progBar, true)
        }
    }

}