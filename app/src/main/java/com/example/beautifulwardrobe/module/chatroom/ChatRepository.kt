package com.example.beautifulwardrobe.module.chatroom

import androidx.lifecycle.MutableLiveData
import com.example.beautifulwardrobe.common.state.State
import com.example.beautifulwardrobe.module.common.repository.ListRepository

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/15 23:26
 */
class ChatRepository(loadState: MutableLiveData<State>) : ListRepository(loadState) {
}