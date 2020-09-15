package com.example.beautifulwardrobe.module.common.repository

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.base.repository.ApiRepository
import com.example.beautifulwardrobe.common.state.State

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