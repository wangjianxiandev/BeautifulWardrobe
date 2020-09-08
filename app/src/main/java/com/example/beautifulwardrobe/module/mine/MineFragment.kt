package com.example.beautifulwardrobe.module.mine

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.example.beautifulwardrobe.MainActivity
import com.example.beautifulwardrobe.R
import com.example.beautifulwardrobe.common.util.CommonUtil
import com.example.beautifulwardrobe.common.util.DataCleanManager

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/9 0:12
 */
class MineFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var parentActivity: MainActivity
    companion object {
        fun getInstance(): MineFragment? {
            return MineFragment()
        }
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_fragment)
        parentActivity = activity as MainActivity
        init()
    }


    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }


    private fun init() {
        val version = "当前版本 " + parentActivity.packageManager.getPackageInfo(
            parentActivity.packageName,
            0
        ).versionName
        findPreference<Preference>("version")?.summary = version
        findPreference<Preference>("clearCache")?.summary =
            DataCleanManager.getTotalCacheSize(parentActivity)
        // 绑定清理缓存响应事件
        findPreference<Preference>("clearCache")?.setOnPreferenceClickListener {
            MaterialDialog(parentActivity).show {
                title(R.string.title)
                message(text = "确定清除缓存吗？")
                cornerRadius(8.0f)
                positiveButton(text = "清除") {
                    DataCleanManager.clearAllCache(parentActivity)
                    findPreference<Preference>("clearCache")?.summary =
                        DataCleanManager.getTotalCacheSize(parentActivity)
                }
                negativeButton(R.string.cancel)
            }
            false
        }

        findPreference<Preference>("csdn")?.setOnPreferenceClickListener {
            CommonUtil.startWebView(parentActivity, "https://blog.csdn.net/qq_39424143", "DLUT_WJX")
            false
        }

        findPreference<Preference>("project")?.setOnPreferenceClickListener {
            CommonUtil.startWebView(
                parentActivity,
                "https://github.com/wangjianxiandev/WanAndroidMvvm",
                "WanAndroid"
            )
            false
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    }
}