package com.example.beautifulwardrobe.module.listcommon.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.base.viewmodel.BaseViewModel
import com.example.beautifulwardrobe.common.initiateRequest
import com.example.beautifulwardrobe.module.collection.model.CollectionData
import com.example.beautifulwardrobe.module.listcommon.repository.ListRepository

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/8 23:41
 */
abstract class ListViewModel<T : ListRepository> :
    BaseViewModel<T>() {
}