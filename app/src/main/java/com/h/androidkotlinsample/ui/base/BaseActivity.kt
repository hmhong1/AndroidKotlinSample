package com.h.androidkotlinsample.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.h.androidkotlinsample.common.Constnets
import com.h.androidkotlinsample.widget.CustomProgressDialog
import java.lang.Exception

open class BaseActivity : AppCompatActivity() {

    private var progressDialog: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.slide_start_enter, R.anim.slide_start_exit)
    }

    override fun finish() {
//        overridePendingTransition(0, 0)
        super.finish()
    }

    open fun showProgressDialog() {
        try {

            progressDialog?.let {
                if(!it.isShowing) {
                    if (!isFinishing) {
                        progressDialog = CustomProgressDialog.show(this)
                    }
                }
            } ?: run {
                if (!isFinishing) {
                    progressDialog = CustomProgressDialog.show(this)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun dismissProgressDialog() {
        try {
            progressDialog?.let {
                if(it.isShowing) {
                    it.dismiss()
                    progressDialog = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
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

    /**
     * 사용법 :
     *  private var introLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ar ->
            Log.v("hhm", "000 "+ar)
            if(ar.resultCode == RESULT_OK) {
                Log.v("hhm", "111111111111")
            }
        }
     *
     *  moveActivityForResult(Intent(this@IntroActivity, MainActivity::class.java), introLauncher)
     *
     *  setResult(RESULT_OK)
     * */
    open fun moveActivityForResult(intent: Intent, launcher : ActivityResultLauncher<Intent>, bundle: Bundle? = null) {
        bundle?.let {
            intent.putExtra(Constnets.KEY_BUNDLE, bundle)
        }
        launcher.launch(intent)
    }

    /**
     * 사용법 : moveActivity(Intent(this, MainActivity::class.java))
     * */
    open fun moveActivity(intent: Intent, bundle: Bundle? = null) {
        bundle?.let {
            intent.putExtra(Constnets.KEY_BUNDLE, bundle)
        }
        startActivity(intent, bundle)
    }

}