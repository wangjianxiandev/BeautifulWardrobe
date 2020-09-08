package com.example.beautifulwardrobe.module.listcommon.adapter

import android.graphics.drawable.Drawable
import android.os.Build
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.beautifulwardrobe.R
import com.example.beautifulwardrobe.module.collection.model.CollectionData

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/9 0:33
 */
open class ListAdapter(layoutId: Int, listData: MutableList<CollectionData>?) :
    BaseQuickAdapter<CollectionData, BaseViewHolder>(layoutId, listData) {
    override fun convert(viewHolder: BaseViewHolder, collectionData: CollectionData?) {
        viewHolder?.let { holder ->
            collectionData?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.setText(R.id.item_collection_type, handleCategory(it))
                        .setBackgroundRes(R.id.item_collection_work_type, handleWorkType(it))
                }
            }
        }
    }

    private fun handleCategory(collection: CollectionData): String {
        collection.let {
            return "分类 : ${it.type}"
        }
    }

    private fun handleWorkType(collection: CollectionData): Int {
        collection.let {
            return when {
                it.isBusy -> R.drawable.title_lable_red
                else -> R.drawable.title_lable_green
            }
        }
    }
}