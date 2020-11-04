package com.cloud.adapter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cloud.adapter.data.WanEntity

/**
 * @Author petterp
 * @Date 2020/10/30-6:53 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@Database(
    entities = [WanEntity::class], version = 2, exportSchema = false
)
abstract class WanDataBase : RoomDatabase() {
    abstract fun wanDao(): WanDao
}