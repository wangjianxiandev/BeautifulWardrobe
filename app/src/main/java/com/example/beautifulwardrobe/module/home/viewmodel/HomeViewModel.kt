package com.wjx.android.wanandroidmvvm.module.system.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.common.initiateRequest
import com.example.beautifulwardrobe.module.common.viewmodel.ListViewModel
import com.example.beautifulwardrobe.module.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/02/27
 * Time: 17:09
 */
class HomeViewModel : ListViewModel<HomeRepository>() {
    val mHomeData: MutableLiveData<List<String>> = MutableLiveData()
    fun loadHomeData() {
        initiateRequest({
            mHomeData.value = withContext(Dispatchers.IO) {
                mRepository.loadHomeData()
            }
        },loadState)
    }
//    val mSystemTabNameData : MutableLiveData<BaseResponse<List<SystemTabNameResponse>>> = MutableLiveData()
//    val mSystemArticleData : MutableLiveData<BaseResponse<SystemArticleResponse>> = MutableLiveData()
//
//    fun loadSystemTab() {
//        mRepository.loadSystemTab(mSystemTabNameData)
//    }
//
//    fun loadSystemArticle(pageNum : Int, cid : Int?) {
//        mRepository.loadSystemArticle(pageNum, cid, mSystemArticleData)
//    }
//
//    val mSystemTabNameData: MutableLiveData<List<SystemTabNameResponse>> = MutableLiveData()
//    val mSystemArticleData: MutableLiveData<SystemArticleResponse> = MutableLiveData()
//
//    fun loadSystemTab() {
//        initiateRequest({ mSystemTabNameData.value = mRepository.loadSystemTabCo() }, loadState)
//    }
//
//    fun loadSystemArticle(pageNum: Int, cid: Int?) {
//        initiateRequest({
//            mSystemArticleData.value = mRepository.loadsystemArticleCo(pageNum, cid)
//        }, loadState)
//    }
}