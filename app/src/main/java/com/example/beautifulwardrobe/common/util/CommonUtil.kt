package com.example.beautifulwardrobe.common.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.beautifulwardrobe.common.startActivity
import com.example.beautifulwardrobe.WebViewActivity
import java.lang.reflect.ParameterizedType

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/3 22:45
 */
object CommonUtil {
    fun showToast(context: Context, string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类泛型 (T) 对应 Class类
        return (t.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
                as Class<T>
    }

    fun getColor(context: Context, color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    fun getNightString(skycon : String) : Boolean {
        return skycon.contains("night", ignoreCase = true)
    }

    fun startWebView(
        context: Context,
        url: String,
        title: String
    ) {
        startActivity<WebViewActivity>(context) {
            putExtra("url", url)
            putExtra("title", title)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}