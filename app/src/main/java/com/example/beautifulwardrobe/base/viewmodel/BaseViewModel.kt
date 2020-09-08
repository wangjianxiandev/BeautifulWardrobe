package com.example.beautifulwardrobe.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautifulwardrobe.base.repository.BaseRepository
import com.example.beautifulwardrobe.common.state.State
import com.example.beautifulwardrobe.common.util.CommonUtil


/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/3 22:51
 */
open class BaseViewModel<T : BaseRepository> : ViewModel(){
    val loadState by lazy {
        MutableLiveData<State>()
    }

    val mRepository : T by lazy {
        (CommonUtil.getClass<T>(this))
            .getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.unSubscribe()
    }
}
