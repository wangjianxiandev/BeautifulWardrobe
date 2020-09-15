package com.example.beautifulwardrobe.module.collection.view

import android.graphics.Color
import androidx.lifecycle.Observer
import com.example.beautifulwardrobe.R
import com.example.beautifulwardrobe.base.view.BaseLifeCycleFragment
import com.example.beautifulwardrobe.common.SpeedLayoutManager
import com.example.beautifulwardrobe.common.util.ColorUtil
import com.example.beautifulwardrobe.module.chatroom.ChatActivity
import com.example.beautifulwardrobe.module.collection.model.CollectionData
import com.example.beautifulwardrobe.module.collection.viewmodel.CollectionViewModel
import com.example.beautifulwardrobe.module.common.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/9 0:31
 */
class CollectionFragment : BaseLifeCycleFragment<CollectionViewModel>(){

    private var mCollectState: Boolean = false

    private var mCurrentItem: Int = 0

    protected lateinit var mAdapter: ListAdapter

    companion object {
        fun getInstance(): CollectionFragment? {
            return CollectionFragment()
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_list

    override fun initView() {
        super.initView()
        initRefresh()
        mRvArticle?.layoutManager = SpeedLayoutManager(context, 10f)
        mAdapter =
            ListAdapter(
                R.layout.collection_item,
                null
            )
        mRvArticle?.adapter = mAdapter

        mAdapter.setOnItemClickListener { _, _, position ->
            com.example.beautifulwardrobe.common.startActivity<ChatActivity>(requireActivity())
        }
        mAdapter.setOnItemChildClickListener { _, _, position ->

        }
        mAdapter.setEnableLoadMore(true)
        mAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)
    }


    private fun initRefresh() {
        // 设置下拉刷新的loading颜色
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(requireContext()))
        mSrlRefresh.setColorSchemeColors(Color.WHITE)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }
    }

    private fun onRefreshData() {
        mViewModel.loadCollectionData()
    }

    override fun initData() {
        super.initData()
        mViewModel.loadCollectionData()
    }

    /**
     * 加载更多数据
     */
    private fun onLoadMoreData() {
        mViewModel.loadCollectionData()
    }

    fun addData(collectionList: List<CollectionData>) {
        // 返回列表为空显示加载完毕
        if (collectionList.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }
        // 如果是下拉刷新状态，直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mAdapter.setNewData(collectionList)
            mAdapter.loadMoreComplete()
            return
        }

        // 初始化状态直接加载数据
        mAdapter.addData(collectionList)
        mAdapter.loadMoreComplete()
    }

    override fun initDataObserver() {
        mViewModel.mCollectionData.observe(this, Observer {
            addData(it)
        })
    }
}