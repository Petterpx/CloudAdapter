package com.petterp.cloud.rvadapter.test.paging.adapter

import androidx.recyclerview.widget.DiffUtil
import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.base.BaseViewHolder
import com.petterp.cloud.rvadapter.test.net.WanListBean
import com.petterp.paging.source.base.BasePageAdapter

/**
 * @Author petterp
 * @Date 2020/10/28-6:12 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class PagingBookAdapter :
    BasePageAdapter<WanListBean.Data, BaseViewHolder>(diff, R.layout.item_android) {
    override fun convert(holder: BaseViewHolder, item: WanListBean.Data) {
        holder.setText(R.id.tvAndroidTitle, item.title)
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<WanListBean.Data>() {
            //id是否相同
            override fun areItemsTheSame(
                oldItem: WanListBean.Data,
                newItem: WanListBean.Data
            ): Boolean {
                return oldItem.chapterId == newItem.chapterId
            }

            //内容是否相同，当[areItemsTheSame]校验通过后，会触发下面方法
            override fun areContentsTheSame(
                oldItem: WanListBean.Data,
                newItem: WanListBean.Data
            ): Boolean {
                //这个例子中，我们只有一个String,所以直接返回true即可
                return oldItem.title == newItem.title
            }

        }
    }
}