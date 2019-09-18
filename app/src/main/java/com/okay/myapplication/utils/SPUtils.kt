package com.okay.myapplication.utils

import android.content.Context

/**
 * Create by zyl
 *
 * @date 2019-09-18
 */
object SPUtils {

    /**
     * 存储数据
     */
    fun put(context: Context, spName: String? = "spUtils", key: String, value: String) {
        val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        sp.edit().putString(key, value).apply()
    }

    fun getString(context: Context, spName: String? = "spUtils", key: String): String {
        val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        return sp.getString(key, "")
    }
}
