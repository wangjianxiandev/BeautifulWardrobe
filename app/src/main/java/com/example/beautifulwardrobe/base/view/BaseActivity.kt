package com.example.beautifulwardrobe.base.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.beautifulwardrobe.R
import com.example.beautifulwardrobe.base.viewmodel.BaseViewModel
import com.example.beautifulwardrobe.common.AppManager
import com.example.beautifulwardrobe.common.util.ColorUtil
import com.example.beautifulwardrobe.common.util.CommonUtil
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import java.util.*

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/3 21:58
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mExitTime: Long = 0

    lateinit var mRootView: View

    val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val modes = window.windowManager.defaultDisplay.supportedModes
            modes.sortBy {
                it.refreshRate
            }
            window.let {
                val layoutParam = it.attributes
                layoutParam.preferredDisplayModeId = modes.last().modeId
                it.attributes = layoutParam
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mRootView = (findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        AppManager.instance.addActivity(this)
        initView()
        initData()
    }


    open fun showCreateReveal(): Boolean = true

    open fun showDestroyReveal(): Boolean = false

    open fun initView() {}
    open fun initData() {}

    abstract fun getLayoutId(): Int

    open fun reLoad() {}

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - mExitTime > 2000) {
            Toast.makeText(this, getString(R.string.exit_app),Toast.LENGTH_SHORT).show()
            mExitTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    /**
     *  设置返回
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }

    private fun initStatusColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.statusBarColor = if (color == 0) ColorUtil.getColor(this) else color
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // 跟随系统
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}