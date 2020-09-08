package com.example.beautifulwardrobe.common

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.beautifulwardrobe.base.BaseApplication
import com.example.beautifulwardrobe.base.app.AppEventViewModel
import com.example.beautifulwardrobe.base.app.AppViewModel

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/9 23:29
 */
fun AppCompatActivity.getAppViewModel(): AppViewModel {
    BaseApplication.instance.let {
        return it.getAppViewModelProvider().get(AppViewModel::class.java)
    }
}

fun Fragment.getAppViewModel(): AppViewModel {
    BaseApplication.instance.let {
        return it.getAppViewModelProvider().get(AppViewModel::class.java)
    }
}

fun Activity.getEventViewModel(): AppEventViewModel {
    BaseApplication.instance.let {
        return it.getAppViewModelProvider().get(AppEventViewModel::class.java)
    }
}