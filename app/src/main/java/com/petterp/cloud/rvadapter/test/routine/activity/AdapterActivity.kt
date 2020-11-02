package com.petterp.cloud.rvadapter.test.routine.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.test.routine.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_adater.*

/**
 * @Author petterp
 * @Date 2020/10/28-5:54 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class AdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val datas = mutableListOf<String>()
        (0..20).forEach { _ ->
            datas.add("Android艺术探索")
        }
        rvMain.adapter = BookAdapter(datas)
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}