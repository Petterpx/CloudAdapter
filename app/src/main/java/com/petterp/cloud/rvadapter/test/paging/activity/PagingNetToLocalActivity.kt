package com.petterp.cloud.rvadapter.test.paging.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.test.paging.adapter.PagingBookAdapter
import com.petterp.cloud.rvadapter.test.paging.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_adater.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author petterp
 * @Date 2020/10/30-5:56 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class PagingNetToLocalActivity : AppCompatActivity() {

    private val viewModel by viewModels<PagingViewModel>()

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val adapter = PagingBookAdapter()
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.initNetToLocal().collectLatest {
                adapter.submitData(it)
            }
        }

    }

}