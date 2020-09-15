package com.example.beautifulwardrobe.module.common.viewmodel

import com.example.beautifulwardrobe.base.viewmodel.BaseViewModel
import com.example.beautifulwardrobe.module.common.repository.ListRepository

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/8 23:41
 */
abstract class ListViewModel<T : ListRepository> :
    BaseViewModel<T>() {
}