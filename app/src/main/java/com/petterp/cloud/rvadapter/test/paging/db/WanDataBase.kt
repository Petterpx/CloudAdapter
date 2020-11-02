package com.petterp.cloud.rvadapter.test.paging.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.petterp.cloud.rvadapter.test.net.WanListBean

/**
 * @Author petterp
 * @Date 2020/10/30-6:53 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */

@Database(
    entities = [WanListBean.Data::class], version = 2, exportSchema = false
)
abstract class WanDataBase : RoomDatabase() {
    abstract fun wanDao(): WanDao
}