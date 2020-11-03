package com.petterp.cloud.rvadapter.test.paging.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petterp.cloud.rvadapter.test.net.WanListBean
import com.petterp.cloud.rvadapter.test.paging.PokemonEntity

/**
 * @Author petterp
 * @Date 2020/10/30-6:47 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@Dao
interface WanDao {

    @Query("SELECT * FROM wan")
    fun queryPage(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<PokemonEntity>)

    @Query("DELETE FROM wan")
    suspend fun clearRepos()
}