package com.petterp.cloud.rvadapter.test.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.petterp.cloud.rvadapter.test.net.ApiServiceImpl
import com.petterp.cloud.rvadapter.test.paging.PokemonEntity
import com.petterp.cloud.rvadapter.test.paging.datasource.BookRemoteMediator
import com.petterp.cloud.rvadapter.test.paging.db.WanDb
import com.petterp.paging.source.ext.StatusRvData
import com.petterp.paging.source.ext.createIndexPagingSource
import com.petterp.paging.source.ext.createPager
import kotlinx.coroutines.flow.Flow

/**
 * @Author petterp
 * @Date 2020/10/30-5:46 PM
 * @Email ShiyihuiCloud@163.com
 * @Function pagingViewModel
 */
@OptIn(ExperimentalPagingApi::class)
class PagingViewModel : ViewModel() {

    val api = ApiServiceImpl.apiService

    /** 初始化 */
    fun initList(): Flow<PagingData<PokemonEntity>> =
        //创建Pager
        createPager{
            //创建数据源
            createIndexPagingSource<PokemonEntity> { it ->
                try {
                    StatusRvData.Success(api.getMainList(it).data.datas.map {
                        PokemonEntity(it.id.toString(), it.title)
                    })
                } catch (e: Exception) {
                    StatusRvData.Error(e)
                }
            }
            // TODO: 2020/10/30 [cachedIn] 缓存数据，保证生命周期切换情况下，数据的完整性
        }.flow.cachedIn(viewModelScope)


    fun initNetToLocal(): Flow<PagingData<PokemonEntity>> =
        createPager(remoteMediator = BookRemoteMediator()) {
            WanDb.dao.wanDao().queryPage()
        }.flow.cachedIn(viewModelScope)


}