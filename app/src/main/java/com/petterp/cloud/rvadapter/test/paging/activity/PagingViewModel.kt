package com.petterp.cloud.rvadapter.test.paging.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.petterp.cloud.rvadapter.test.net.ApiServiceImpl
import com.petterp.cloud.rvadapter.test.net.WanListBean
import com.petterp.cloud.rvadapter.test.paging.datasource.BookRemoteMediator
import com.petterp.cloud.rvadapter.test.paging.db.WanDb
import com.petterp.paging.source.ktx.CloudNetData
import com.petterp.paging.source.ktx.createIndexPagingSource
import com.petterp.paging.source.ktx.createPager
import kotlinx.coroutines.flow.Flow

/**
 * @Author petterp
 * @Date 2020/10/30-5:46 PM
 * @Email ShiyihuiCloud@163.com
 * @Function pagingViewModel
 */
class PagingViewModel : ViewModel() {

    val api = ApiServiceImpl.apiService

    /** 初始化 */
    fun initList(): Flow<PagingData<WanListBean.Data>> =
        //创建Pager
        createPager {
            //创建数据源
            createIndexPagingSource<WanListBean.Data> {
                try {
                    CloudNetData.Success(api.getMainList(it).data.datas)
                } catch (e: Exception) {
                    CloudNetData.Error(e)
                }
            }
            // TODO: 2020/10/30 [cachedIn] 缓存数据，保证PagingData的相同
        }.flow.cachedIn(viewModelScope)


    @ExperimentalPagingApi
    fun initNetToLocal(): Flow<PagingData<WanListBean.Data>> =
        createPager(remoteMediator = BookRemoteMediator()) {
            WanDb.dao.wanDao().queryPage()
        }.flow.cachedIn(viewModelScope)

}