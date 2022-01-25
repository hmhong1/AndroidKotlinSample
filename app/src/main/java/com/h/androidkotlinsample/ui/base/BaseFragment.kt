package com.h.androidkotlinsample.ui.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.ui.main.MainActivity
import com.h.androidkotlinsample.widget.CustomProgressDialog

open class BaseFragment : Fragment() {

    private var progressDialog: CustomProgressDialog? = null

    companion object {
        private const val TAG = "BaseFragment"
    }

    open fun showProgressDialog() {
        progressDialog.runCatching {
            if (progressDialog == null || !progressDialog!!.isShowing) {
                progressDialog = CustomProgressDialog.show(requireActivity())
            }
        }.onFailure {
            Log.e(TAG, it.toString())
        }
    }

    open fun dismissProgressDialog() {
        progressDialog.runCatching {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        }.onFailure {
            Log.e(TAG, it.toString())
        }
    }

    /**
     * 프로그레스바 사용 설정
     * */
    open fun progressDialogInit(viewModel : BaseViewModel) {
        viewModel.isProgressDialogLiveData.observe(this) { aBoolean ->
            if (aBoolean) {
                showProgressDialog()
            } else {
                dismissProgressDialog()
            }
        }
    }

    open fun replaceFragment(fragment: Fragment, bundle: Bundle? = null, tag: String? = null) {
        (activity as MainActivity).replaceFragment(fragment, bundle, tag)
    }
}