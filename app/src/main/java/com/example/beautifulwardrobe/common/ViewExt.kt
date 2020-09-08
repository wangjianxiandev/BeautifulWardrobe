package com.example.beautifulwardrobe.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/9 21:50
 */
fun ViewPager.init(
    fragmentManager: FragmentManager,
    fragments: ArrayList<Fragment>
): ViewPager {
    //设置适配器
    adapter = object : FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int) = fragments[position]
        override fun getCount() = fragments.size
    }
    return this
}
