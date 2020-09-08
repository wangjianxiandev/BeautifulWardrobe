package com.example.beautifulwardrobe

import android.os.Build
import android.text.Html
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import com.example.beautifulwardrobe.base.view.BaseActivity
import com.example.beautifulwardrobe.common.util.ColorUtil
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.custom_bar.*


class WebViewActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mAgentWeb: AgentWeb

    private val mUrl: String? by lazy { intent?.getStringExtra("url") }

    private val mTitle: String? by lazy { intent?.getStringExtra("title") }

    private lateinit var mPopupWindow: PopupWindow

    override fun getLayoutId(): Int = R.layout.activity_web_view

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initView() {
        initHeaderView()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(web_view_detail, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(mUrl)
    }

    private fun initHeaderView() {
        custom_bar.setBackgroundColor(ColorUtil.getColor(this))
        detail_back.apply {
            visibility = View.VISIBLE
            setOnClickListener(this@WebViewActivity)
        }
        detail_title.text =
            Html.fromHtml(intent.getStringExtra("title"), Html.FROM_HTML_MODE_COMPACT)
    }

    override fun showDestroyReveal(): Boolean = false

    override fun onBackPressed() = finish()

    override fun onClick(v: View?) {
        v?.let { v ->
            when (v.id) {
                R.id.detail_back -> {
                    onBackPressed()
                }
            }
        }
    }
}