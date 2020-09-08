package com.example.beautifulwardrobe.module.collection.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.common.initiateRequest
import com.example.beautifulwardrobe.module.collection.repository.CollectionRepository
import com.example.beautifulwardrobe.module.collection.model.CollectionData
import com.example.beautifulwardrobe.module.listcommon.viewmodel.ListViewModel

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/9 0:53
 */
class CollectionViewModel : ListViewModel<CollectionRepository>() {
    val mCollectionData : MutableLiveData<List<CollectionData>> = MutableLiveData()
    fun loadCollectionData() {
        initiateRequest({mCollectionData.value =
            mRepository.loadCollectionData()},loadState)
    }
}