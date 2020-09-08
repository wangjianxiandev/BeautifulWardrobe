package com.example.beautifulwardrobe.base.app

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.base.repository.ApiRepository
import com.example.beautifulwardrobe.common.state.State

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/4 12:25
 */

class AppRepository(var loadState: MutableLiveData<State>) : ApiRepository() {
}