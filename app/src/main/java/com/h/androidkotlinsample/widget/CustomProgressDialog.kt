package com.h.androidkotlinsample.widget

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.h.androidkotlinsample.R

class CustomProgressDialog(context : Context) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_custom_progress)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

//        ImageView loadingView = findViewById(R.id.loading_image)
//        Glide.with(mContext).asGif().load(R.drawable.loading).into(loadingView)
    }

    fun show(activity: Activity?): CustomProgressDialog? {
        val dialog = CustomProgressDialog(activity!!)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }

    override fun show() {
        val window = window
        window?.let {
            it.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        super.show()
    }
}