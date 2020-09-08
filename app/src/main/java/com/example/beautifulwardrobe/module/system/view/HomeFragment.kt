package com.example.beautifulwardrobe.module.system.view

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.beautifulwardrobe.R
import com.example.beautifulwardrobe.base.view.BaseLifeCycleFragment
import com.example.beautifulwardrobe.common.util.ColorUtil
import com.example.beautifulwardrobe.module.system.adapter.HomeAdapter
import com.wjx.android.wanandroidmvvm.module.system.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/02/27
 * Time: 17:01
 */
class HomeFragment : BaseLifeCycleFragment<HomeViewModel>() {
    protected lateinit var mAdapter: HomeAdapter
    private lateinit var mTitleList : ArrayList<String>

    companion object {
        fun getInstance(): HomeFragment? {
            return HomeFragment()
        }
    }

    override fun initDataObserver() {
        mViewModel.mHomeData.observe(this, Observer {
            setHomeData(it)
        })
    }

    override fun initData() {
        mViewModel.loadHomeData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_list

    override fun initView() {
        super.initView()
        initRefresh()
        mRvArticle?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = HomeAdapter(R.layout.home_item, null)
        mRvArticle.adapter = mAdapter
        mAdapter.setOnItemChildClickListener { _, _, position ->
            val item = mAdapter.getItem(position)
            item?.let {
            }
        }
    }

    private fun initRefresh() {
        // 设置下拉刷新的loading颜色
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(requireContext()))
        mSrlRefresh.setColorSchemeColors(Color.WHITE)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }
    }

    private fun onRefreshData() {
        mViewModel.loadHomeData()
    }

    private fun setHomeData(systemListName : List<String>) {
        val chileItems = arrayListOf<String>()
        // 返回列表为空显示加载完毕
        if (systemListName.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }

        // 如果是下拉刷新状态，直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mAdapter.setNewData(systemListName)
            mAdapter.loadMoreComplete()
            return
        }

        // 初始化状态直接加载数据
        mAdapter.addData(systemListName)
        mAdapter.loadMoreComplete()
    }
}