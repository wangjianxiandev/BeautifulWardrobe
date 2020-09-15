package com.example.beautifulwardrobe.module.collection.repository

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.common.state.State
import com.example.beautifulwardrobe.module.collection.model.CollectionData
import com.example.beautifulwardrobe.module.common.repository.ListRepository

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/9 0:54
 */
class CollectionRepository (loadState: MutableLiveData<State>) : ListRepository(loadState) {
    suspend fun loadCollectionData(): List<CollectionData> {
        val collectionData = ArrayList<CollectionData>()
        for (i in 0 until 30) {
            collectionData.add(
                CollectionData(
                    if (i % 2 == 0) "职场" else "休闲",
                    if (i % 2 == 0) true else false
                )
            )
        }
        return collectionData

    }
}