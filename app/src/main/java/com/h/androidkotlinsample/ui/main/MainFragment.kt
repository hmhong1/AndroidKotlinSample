package com.h.androidkotlinsample.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.FragmentMainBinding
import com.h.androidkotlinsample.databinding.MainActionbarLayoutBinding
import com.h.androidkotlinsample.ui.base.BaseFragment
import com.h.androidkotlinsample.ui.web.CommonWebFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private var mContext : Context? = null

    private lateinit var mainActionbarBinding : MainActionbarLayoutBinding
    private lateinit var binding : FragmentMainBinding
    private val viewModel by viewModels<MainFragViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this@MainFragment

        mainActionbarBinding = binding.mainActionbarLayout
        mainActionbarBinding.activity = activity as MainActivity

        mContext = activity

        progressDialogInit(viewModel!!)
        init()
        observers()

        return binding.root
    }

    private fun init() {
        mainActionbarBinding.actionBarBtnBack.visibility = View.VISIBLE
        mainActionbarBinding.actionBarBtnMenu.visibility = View.VISIBLE
    }

    private fun observers() {
//        viewModel!!.textValue.observe(this, Observer<String> {
//
//        })
    }

    fun onBtnClick() {
        (activity as MainActivity).drawerOpen()
    }

    fun onClickBtnWebview() {
        replaceFragment(CommonWebFragment(), CommonWebFragment::class.java.simpleName)
    }
}