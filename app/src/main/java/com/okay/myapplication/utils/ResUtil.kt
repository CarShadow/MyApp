package com.okay.myapplication.utils

import android.content.Context

/**
 * Create by zyl
 * @date 2019-09-18
 */
object ResUtil {

    //获取指定context的 type里面的 name属性
    fun getResourceId(context: Context?,type:String,name:String):Int?{
        return context?.resources?.getIdentifier(name,type,context.packageName)
    }
}