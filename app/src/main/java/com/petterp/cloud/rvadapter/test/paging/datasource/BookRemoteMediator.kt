package com.petterp.cloud.rvadapter.test.paging.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.petterp.cloud.rvadapter.test.KtxApplication
import com.petterp.cloud.rvadapter.test.net.ApiServiceImpl
import com.petterp.cloud.rvadapter.test.paging.PokemonEntity
import com.petterp.cloud.rvadapter.test.paging.db.WanDb
import com.petterp.paging.source.ext.isConnectedNetwork

/**
 * @Author petterp
 * @Date 2020/10/30-6:25 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator : RemoteMediator<Int, PokemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        var page = when (loadType) {
            // TODO: 2020/10/30 首次访问，或者调用 PagingDataAdapter.refresh() 时调用
            LoadType.REFRESH -> {
                null
            }

            // TODO: 2020/10/30 在当前加载的数据集的开头加载数据时调用,[endOfPaginationReached]是否还有数据
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                state.lastItemOrNull()?.page
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        } ?: 0

        if (!KtxApplication.context.isConnectedNetwork()) {
            Log.e("petterpx", "无网络")
            // 无网络加载本地数据
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        //加载网络数据
        Log.e("petterpx", "当前type${loadType.name}+$page")
        return try {
            val data = ApiServiceImpl.apiService.getMainList(page).data.datas.also {
                ++page
            }.map {
                PokemonEntity(
                    it.id.toString(),
                    it.title,
                    page
                )
            }
            WanDb.dao.apply {
                withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        this.wanDao().clearRepos()
                    }
                    this.wanDao().insertAll(data)
                }
            }
            MediatorResult.Success(endOfPaginationReached = data.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }
}