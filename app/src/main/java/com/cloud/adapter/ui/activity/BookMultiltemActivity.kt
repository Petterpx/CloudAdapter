package com.cloud.adapter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.adapter.data.model.WanModel
import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.ui.adapter.BookMultiItemAdapter
import kotlinx.android.synthetic.main.activity_adater.*

/**
 * @Author petterp
 * @Date 2020/10/28-5:54 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class BookMultiltemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val datas = mutableListOf<WanModel>()
        (0..20).forEach { _ ->
            datas.add(WanModel.ImageToText("我是名字", R.mipmap.android))
            datas.add(WanModel.ImageToText("我是名字", R.mipmap.android))
            datas.add(WanModel.SingleText("Android艺术探索"))
        }
        rvMain.adapter= BookMultiItemAdapter(datas)
        rvMain.layoutManager=LinearLayoutManager(this)
    }
}