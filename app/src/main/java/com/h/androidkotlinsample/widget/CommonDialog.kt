package com.h.androidkotlinsample.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.DialogCommonBinding

class CommonDialog(context : Context) : Dialog(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert), View.OnClickListener {

    private val TAG = CommonDialog::class.java.simpleName
    private var mContext : Context? = context

    private var binding : DialogCommonBinding? = null

    init {
        var view = LayoutInflater.from(mContext).inflate(R.layout.dialog_common, null, false)
        binding = DialogCommonBinding.bind(view)
        binding!!.dialogView = this@CommonDialog
        setContentView(binding!!.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun canceledOnTouchOutside(isCancel : Boolean = true) = setCanceledOnTouchOutside(isCancel)
    fun cancelable(isCancel : Boolean = true) = setCancelable(isCancel)

    /**
     * 버튼 1개
     * */
    fun showCommonDialog(title : String?, content: String?, btnRight : String?, rightOnClickListener : View.OnClickListener?) {
        binding?.let {
            if(!title.isNullOrEmpty()) {
                it.titleLayout.visibility = View.VISIBLE
                it.txTitle.text = title
            }
            if(!content.isNullOrEmpty()) {
                it.txContent.text = content
            }
            if(!btnRight.isNullOrEmpty()) {
                it.btnRight.text = btnRight
            }

            rightOnClickListener?.let { vi ->
                it.btnRight.setOnClickListener(vi)
            } ?: run {
                it.btnRight.setOnClickListener(this)
            }
        }
    }

    /**
     * 버튼 2개
     * */
    fun showCommonDialog(title : String?, content: String?, btnLeft : String?, btnRight : String?, leftOnClickListener : View.OnClickListener?, rightOnClickListener : View.OnClickListener?) {
        binding?.let {
            if(!title.isNullOrEmpty()) {
                it.titleLayout.visibility = View.VISIBLE
                it.txTitle.text = title
            }
            if(!content.isNullOrEmpty()) {
                it.txContent.text = content
            }
            if(!btnLeft.isNullOrEmpty()) {
                it.btnLeft.visibility = View.VISIBLE
                it.btnLeft.text = btnLeft
            }
            if(!btnRight.isNullOrEmpty()) {
                it.btnRight.text = btnRight
            }

            leftOnClickListener?.let { vi ->
                it.btnLeft.setOnClickListener(vi)
            } ?: run {
                it.btnLeft.setOnClickListener(this)
            }

            rightOnClickListener?.let { vi ->
                it.btnRight.setOnClickListener(vi)
            } ?: run {
                it.btnRight.setOnClickListener(this)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnLeft -> {
            }
            R.id.btnRight -> {
            }
        }
        dismiss()
    }
}