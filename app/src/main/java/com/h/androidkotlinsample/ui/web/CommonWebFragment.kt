package com.h.androidkotlinsample.ui.web

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.common.Constnets
import com.h.androidkotlinsample.databinding.FragmentCommonWebBinding
import com.h.androidkotlinsample.databinding.MainActionbarLayoutBinding
import com.h.androidkotlinsample.interfaces.BackPressedInterface
import com.h.androidkotlinsample.ui.base.BaseFragment
import com.h.androidkotlinsample.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommonWebFragment : BaseFragment(), BackPressedInterface {

    private var mContext : Context? = null

    private lateinit var mainActionbarBinding : MainActionbarLayoutBinding
    private lateinit var binding : FragmentCommonWebBinding
    private val viewModel by viewModels<CommonWebViewModel>()

    private var webUrl = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_common_web, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mainActionbarBinding = binding.mainActionbarLayout
        mainActionbarBinding.activity = activity as MainActivity

        mContext = activity

        progressDialogInit(viewModel!!)

        getArgument()
        init()

        return binding.root
    }

    private fun getArgument() {
        arguments?.let {
            webUrl = it.getString(Constnets.KEY_WEB_URL, "")
        }
    }

    private fun init() {
        mainActionbarBinding.actionBarBtnBack.visibility = View.VISIBLE
        mainActionbarBinding.actionBarBtnMenu.visibility = View.VISIBLE

        webSettings(binding.webView)

        if(!webUrl.isNullOrEmpty())
            binding.webView.loadUrl(webUrl)
    }

    private fun webSettings(wb: WebView) {
        wb.isHorizontalScrollBarEnabled = false
        wb.isVerticalScrollBarEnabled = false

        wb.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
            loadsImagesAutomatically = true
            useWideViewPort = true
            loadWithOverviewMode = true
            setSupportZoom(true)

            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

            cacheMode = WebSettings.LOAD_DEFAULT

            if (android.os.Build.VERSION.SDK_INT >= 21) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                var cookieManager = CookieManager.getInstance()
                cookieManager.setAcceptCookie(true)
                cookieManager.setAcceptThirdPartyCookies(wb, true)
            }

        }

        wb.webViewClient = webViewClient
        wb.webChromeClient = webChromeClient
    }

    private var webChromeClient = object : WebChromeClient() {

        override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }
    }

    private var webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            showProgressDialog()
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            dismissProgressDialog()
            super.onPageFinished(view, url)
        }
    }

    override fun onBackPressed(): Boolean {
        binding.webView?.let {
            if(it.canGoBack()) {
                it.goBack()
                return true
            }
        }
        return false
    }
}