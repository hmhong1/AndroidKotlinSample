package com.h.androidkotlinsample.common

import android.content.Context
import android.content.pm.PackageManager

class Util {
    companion object {

        /**
         * appversion 가져오기
         * */
        fun getAppVersion(context: Context) : String {
            var versionName = ""

            try {
                var info = context.packageManager.getPackageInfo(context.packageName, 0)
                versionName = info.versionName
            } catch (e : PackageManager.NameNotFoundException) {
                DLog.d("appversion : $e")
            }

            return versionName
        }
    }
}