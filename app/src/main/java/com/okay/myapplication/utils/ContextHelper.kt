package com.okay.myapplication.utils

import android.content.Context
import android.content.pm.PackageManager

/**
 * Create by zyl
 * @date 2019-09-18
 */
object ContextHelper {

    fun getPackageContext(context: Context, packageName: String): Context? {
        try {
            return context.createPackageContext(
                packageName,
                Context.CONTEXT_INCLUDE_CODE or Context.CONTEXT_IGNORE_SECURITY
            )
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}