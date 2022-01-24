package com.h.androidkotlinsample.common

import android.util.Log
import com.h.androidkotlinsample.BuildConfig

class DLog {
    companion object {

        private val TAG = "AndroidKotlinSample"

        fun v(msg : String) {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, msg)
            }
        }
        fun v(tag : String?, msg : String) {
            if (BuildConfig.DEBUG) {
                Log.v(tag, msg)
            }
        }

        fun d(msg : String) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, msg)
            }
        }
        fun d(tag : String?, msg : String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, msg)
            }
        }

        fun i(msg : String) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, msg)
            }
        }
        fun i(tag : String?, msg : String) {
            if (BuildConfig.DEBUG) {
                Log.i(tag, msg)
            }
        }

        fun w(msg : String) {
            if (BuildConfig.DEBUG) {
                Log.w(TAG, msg)
            }
        }
        fun w(tag : String?, msg : String) {
            if (BuildConfig.DEBUG) {
                Log.w(tag, msg)
            }
        }

        fun e(msg : String) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, msg)
            }
        }
        fun e(tag : String?, msg : String) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, msg)
            }
        }
    }
}