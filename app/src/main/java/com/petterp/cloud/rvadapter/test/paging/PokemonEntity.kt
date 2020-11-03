package com.petterp.cloud.rvadapter.test.paging

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.petterp.cloud.rvadapter.test.net.WanListBean

/**
 * @Author petterp
 * @Date 2020/11/3-2:43 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@Entity(tableName = "wan")
data class PokemonEntity(@PrimaryKey val id: String, val title: String, val page: Int = 0) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<PokemonEntity>() {
            //id是否相同
            override fun areItemsTheSame(
                oldItem: PokemonEntity,
                newItem: PokemonEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            //内容是否相同，当[areItemsTheSame]校验通过后，会触发下面方法
            override fun areContentsTheSame(
                oldItem: PokemonEntity,
                newItem: PokemonEntity
            ): Boolean {
                //这个例子中，我们只有一个String,所以直接返回true即可
                return oldItem.title == newItem.title
            }

        }
    }
}