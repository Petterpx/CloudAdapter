package com.cloud.adapter.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author petterp
 * @Date 2020/11/3-2:43 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 数据库示例
 */
@Entity(tableName = "wan")
data class WanEntity(@PrimaryKey val id: String, val title: String, val page: Int = 0) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<WanEntity>() {
            //id是否相同
            override fun areItemsTheSame(
                oldItem: WanEntity,
                newItem: WanEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            //内容是否相同，当[areItemsTheSame]校验通过后，会触发下面方法
            override fun areContentsTheSame(
                oldItem: WanEntity,
                newItem: WanEntity
            ): Boolean {
                //这个例子中，我们只有一个String,所以直接返回true即可
                return oldItem.title == newItem.title
            }

        }
    }
}