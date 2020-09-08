package com.example.beautifulwardrobe.module.system.repository

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.common.state.State
import com.example.beautifulwardrobe.module.listcommon.repository.ListRepository

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/02/27
 * Time: 17:09
 */
class HomeRepository(loadState: MutableLiveData<State>) : ListRepository(loadState) {
    suspend fun loadHomeData() : List<String> {
        val titleList = ArrayList<String>()
        for(i in 0..30) {
            titleList.add("82年的小王~")
        }
        return titleList
    }
}