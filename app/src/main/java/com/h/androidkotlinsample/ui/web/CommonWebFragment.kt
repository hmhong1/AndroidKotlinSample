package com.h.androidkotlinsample.ui.web

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.FragmentCommonWebBinding
import com.h.androidkotlinsample.databinding.MainActionbarLayoutBinding
import com.h.androidkotlinsample.ui.base.BaseFragment
import com.h.androidkotlinsample.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommonWebFragment : BaseFragment() {

    private var mContext : Context? = null

    private lateinit var mainActionbarBinding : MainActionbarLayoutBinding
    private lateinit var binding : FragmentCommonWebBinding
    private val viewModel by viewModels<CommonWebViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_common_web, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this@CommonWebFragment

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
    }
}