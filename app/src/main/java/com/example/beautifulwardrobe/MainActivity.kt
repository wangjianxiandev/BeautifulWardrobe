package com.example.beautifulwardrobe

import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.beautifulwardrobe.base.view.BaseActivity
import com.example.beautifulwardrobe.common.Constant
import com.example.beautifulwardrobe.common.util.ColorUtil
import com.example.beautifulwardrobe.module.collection.view.CollectionFragment
import com.example.beautifulwardrobe.module.mine.MineFragment
import com.example.beautifulwardrobe.module.system.view.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {

    private var mLastIndex: Int = -1
    private val mFragmentSparseArray = SparseArray<Fragment>()

    // 当前显示的 fragment
    private var mCurrentFragment: Fragment? = null
    private var mLastFragment: Fragment? = null

    private lateinit var mToolbarTitles: List<String>

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        initToolbarTitles()
        initToolBar()
        initDrawerLayout()
        initFabButton()
//        initColor()
        initBottomNavigation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        // 判断当前是recreate还是新启动
        if (savedInstanceState == null) {
            switchFragment(Constant.HOME)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // recreate时保存当前页面位置
        outState.putInt("index", mLastIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 恢复recreate前的页面
        mLastIndex = savedInstanceState.getInt("index")
        switchFragment(mLastIndex)
    }

    private fun initToolbarTitles() {
        mToolbarTitles = arrayListOf(
            getString(R.string.navigation_home),
            getString(R.string.navigation_collocation),
            getString(R.string.navigation_mine)
        )
    }

    private fun initToolBar() {
        //设置导航图标、按钮有旋转特效
        val toggle = ActionBarDrawerToggle(
            this, home_main, toolbar, R.string.app_name, R.string.app_name
        )
        home_main.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initDrawerLayout() {

        navigation_draw.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_rank -> {
                    Toast.makeText(this@MainActivity, "setting", Toast.LENGTH_SHORT).show()
                }
            }
            // 关闭侧边栏
            home_main.closeDrawers()
            true
        }
    }

    private fun initColor() {
        toolbar.setBackgroundColor(ColorUtil.getColor(this))
        bottom_navigation.setItemIconTintList(ColorUtil.getColorStateList(this))
        bottom_navigation.setItemTextColor(ColorUtil.getColorStateList(this))
        bottom_navigation.setBackgroundColor(ContextCompat.getColor(this, R.color.white_bg))
        fab_add.setBackgroundTintList(ColorUtil.getOneColorStateList(this))
    }

    private fun initFabButton() {
        fab_add.setOnClickListener {

        }
    }

    private fun initBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    fab_add.visibility = View.VISIBLE
                    switchFragment(Constant.HOME)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_collection -> {
                    fab_add.visibility = View.GONE
                    switchFragment(Constant.COLLECTION)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_mine -> {
                    fab_add.visibility = View.GONE
                    switchFragment(Constant.MINE)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun switchFragment(index: Int) {
        val fragmentManager = supportFragmentManager
        val transaction =
            fragmentManager.beginTransaction()
        // 将当前显示的fragment和上一个需要隐藏的fragment分别加上tag, 并获取出来
        // 给fragment添加tag,这样可以通过findFragmentByTag找到存在的fragment，不会出现重复添加
        mCurrentFragment = fragmentManager.findFragmentByTag(index.toString())
        mLastFragment = fragmentManager.findFragmentByTag(mLastIndex.toString())
        // 如果位置不同
        if (index != mLastIndex) {
            if (mLastFragment != null) {
                transaction.hide(mLastFragment!!)
            }
            if (mCurrentFragment == null) {
                mCurrentFragment = getFragment(index)
                transaction.add(R.id.content, mCurrentFragment!!, index.toString())
            } else {
                transaction.show(mCurrentFragment!!)
            }
        }

        // 如果位置相同或者新启动的应用
        if (index == mLastIndex) {
            if (mCurrentFragment == null) {
                mCurrentFragment = getFragment(index)
                transaction.add(R.id.content, mCurrentFragment!!, index.toString())
            }
        }
        transaction.commit()
        mLastIndex = index
        setToolBarTitle(toolbar, mToolbarTitles[mLastIndex])
    }

    private fun getFragment(index: Int): Fragment {
        var fragment: Fragment? = mFragmentSparseArray.get(index)
        if (fragment == null) {
            when (index) {
                Constant.HOME -> fragment = HomeFragment.getInstance()
                Constant.COLLECTION -> fragment = CollectionFragment.getInstance()
                Constant.MINE -> fragment = MineFragment.getInstance()
            }
            mFragmentSparseArray.put(index, fragment)
        }
        return fragment!!
    }

    fun setToolBarTitle(toolbar: Toolbar, title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }
}