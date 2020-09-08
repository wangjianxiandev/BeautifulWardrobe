package com.example.beautifulwardrobe.module.listcommon.repository

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.base.repository.ApiRepository
import com.example.beautifulwardrobe.common.state.State
import com.example.beautifulwardrobe.module.collection.model.CollectionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class ListRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

//
//    suspend fun unCollectCo(id : Int ) :EmptyResponse {
//        return withContext(Dispatchers.IO) {
//            apiService.unCollectCo(id).dataConvert(loadState)
//        }
//    }
//
//    suspend fun insertFootPrint(article: Article) = RoomHelper.insertFootPrint(article)
}