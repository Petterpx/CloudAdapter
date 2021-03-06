package com.cloud.adapter.base

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.cloud.adapter.base.listener.OnItemChildClickListener
import com.cloud.adapter.base.listener.OnItemChildLongClickListener
import com.cloud.adapter.base.listener.OnItemClickListener
import com.cloud.adapter.base.listener.OnItemLongClickListener
import com.cloud.adapter.expand.IAdapterExt
import com.cloud.adapter.expand.getItemView
import java.util.LinkedHashSet

/**
 * @Author petterp
 * @Date 2020/8/2-3:16 PM
 * @Email ShiyihuiCloud@163.com
 * [T] 数据模型
 * [VH] BaseViewHolder
 * @Function 基础BaseQuickAdapter
 */
@SuppressLint("UseSparseArrays")
abstract class BaseQuickAdapter<T : Any, VH : BaseViewHolder>
@JvmOverloads constructor(
    @LayoutRes private val layoutResId: Int, data: MutableList<T>? = null
) :
    RecyclerView.Adapter<VH>(),IAdapterExt {

    var data: MutableList<T> = data ?: arrayListOf()
        internal set

    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private var mOnItemChildLongClickListener: OnItemChildLongClickListener? = null
    private var mOnItemChildClickListener: OnItemChildClickListener? = null

    /**
     * 用于保存需要设置点击事件的 item
     */
    private val childClickViewIds by lazy {
        LinkedHashSet<Int>()
    }

    /**
     * 设置需要点击事件的子view
     * @param viewIds IntArray
     */
    fun addChildClickViewIds(@IdRes vararg viewIds: Int) {
        for (viewId in viewIds) {
            childClickViewIds.add(viewId)
        }
    }

    /** 设置ItemType */
    override fun getItemViewType(position: Int): Int {
        // TODO: 2020/10/27 这里可以增加header或者footer
        return layoutResId
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        // TODO: 2020/8/2 这里可以做一些header,footer的判断，暂时没做
        convert(holder, getItem(position))
    }

    open fun getItem(@IntRange(from = 0) position: Int): T {
        return data[position]
    }

    /** 返回-1,表示不存在 */
    open fun getItemPosition(item: T?): Int {
        return if (item != null && data.isNotEmpty()) data.indexOf(item) else -1
    }

    override fun getItemCount(): Int {
        return data.size
    }

//    open fun getItemOrNull(@IntRange(from = 0)position: Int):T?{
//        return data
//    }

    /** item数据的加载 */
    protected abstract fun convert(holder: VH, item: T)

    /** viewType=layout */
    override fun `onCreateViewHolder`(parent: ViewGroup, viewType: Int): VH {
        // TODO: 2020/8/2 暂时未处理header与footer及其他情况，暂时实现单一的
        val viewHolder = onCreateDefViewHolder(parent, viewType)
        bindViewClickListener(viewHolder, viewType)
        onItemViewHolderCreated(viewHolder, viewType)
        return viewHolder
    }

    /** viewHolder创建完成后的一些绑定操作 */
    protected open fun onItemViewHolderCreated(viewHolder: VH, viewType: Int) {}

    /** 点击事件的处理 */
    open fun bindViewClickListener(viewHolder: VH, viewType: Int) {
        mOnItemClickListener?.let {
            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) return@setOnClickListener
                setItemClick(it, position)
            }
        }
        mOnItemLongClickListener?.let {
            viewHolder.itemView.setOnLongClickListener {
                val position = viewHolder.bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) return@setOnLongClickListener false
                setItemLongClick(it, position)
            }
        }

        mOnItemChildClickListener?.let {
            for (id in childClickViewIds) {
                viewHolder.itemView.findViewById<View>(id)?.let { childView ->
                    if (!childView.isClickable) childView.isClickable = false
                    childView.setOnClickListener {
                        val position = viewHolder.bindingAdapterPosition
                        if (position == RecyclerView.NO_POSITION) return@setOnClickListener
                        setItemChildClick(it, position)
                    }
                }
            }
        }

        mOnItemChildClickListener?.let {
            for (id in childClickViewIds) {
                viewHolder.itemView.findViewById<View>(id)?.let { childView ->
                    if (!childView.isClickable) childView.isClickable = false
                    childView.setOnLongClickListener {
                        val position = viewHolder.bindingAdapterPosition
                        if (position == RecyclerView.NO_POSITION) return@setOnLongClickListener false
                        setItemChildLongClick(it, position)
                    }
                }
            }
        }
    }

    /** 可以重写此方法实现自定义child点击 */
    protected open fun setItemChildClick(v: View, position: Int) {
        mOnItemChildClickListener?.onItemChildClick(this, v, position)
    }

    /** 可以重写此方法实现自定义child-long点击 */
    protected open fun setItemChildLongClick(v: View, position: Int): Boolean {
        return mOnItemChildLongClickListener?.onItemChildLongClick(this, v, position) ?: false
    }

    /** 可以重写此方法实现自定义item点击 */
    protected open fun setItemClick(v: View, position: Int) {
        mOnItemClickListener?.onItemClick(this, v, position)
    }

    /** 可以重写此方法实现自定义item-long点击 */
    protected open fun setItemLongClick(v: View, position: Int): Boolean {
        return mOnItemLongClickListener?.onItemLongClick(this, v, position) ?: false
    }

    /**
     * Override this method and return your ViewHolder.
     * 重写此方法，返回你的ViewHolder。
     */
    protected open fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createBaseViewHolder(parent, viewType)
    }

    protected open fun createBaseViewHolder(parent: ViewGroup, @LayoutRes layoutResId: Int): VH {
        return createBaseViewHolder(this, parent.getItemView(layoutResId))
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = itemClickListener
    }

    fun setItemLongClickListener(itemClickListener: OnItemLongClickListener) {
        this.mOnItemLongClickListener = itemClickListener
    }

    fun setItemChildClickListener(itemChildClickListener: OnItemChildClickListener) {
        this.mOnItemChildClickListener = itemChildClickListener
    }

    fun setItemChildLongClickListener(itemChildLongClickListener: OnItemChildLongClickListener) {
        this.mOnItemChildLongClickListener = itemChildLongClickListener
    }
}