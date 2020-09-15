package com.example.beautifulwardrobe.module.chatroom.model

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/9/15 23:33
 */
data class MessageData(
    var userIp : String,
    var belongIp : String,
    var text : String,
    var time : String,
    var imagePath : String,
    var audioPath : String,
    var filePath : String,
    var fileName : String,
    var fileSize : Int,
    var fileState : Int,
    var msgType : Int
)