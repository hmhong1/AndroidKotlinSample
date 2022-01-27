package com.h.androidkotlinsample.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.common.Constnets
import com.h.androidkotlinsample.databinding.FragmentMainBinding
import com.h.androidkotlinsample.databinding.MainActionbarLayoutBinding
import com.h.androidkotlinsample.ui.base.BaseFragment
import com.h.androidkotlinsample.ui.web.CommonWebFragment
import com.h.androidkotlinsample.widget.CommonDialog
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
        var bundle = Bundle()
        bundle.putString(Constnets.KEY_WEB_URL, "https://www.google.com")
        replaceFragment(CommonWebFragment(), bundle, CommonWebFragment::class.java.simpleName)
    }
    fun onClickBtnCustomDialog1() {
        var dialog = CommonDialog(requireContext())
        dialog.showCommonDialog("", "custom dialog 1 content", "OK", View.OnClickListener {
            Toast.makeText(requireContext(), "OK onclick", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        })
        dialog.show()
    }
    fun onClickBtnCustomDialog2() {
        var dialog = CommonDialog(requireContext())
        dialog.cancelable(false)
        dialog.canceledOnTouchOutside(false)
        dialog.showCommonDialog("title", "custom dialog 2 content", "Cancel", "OK",
            View.OnClickListener {
                Toast.makeText(requireContext(), "Cancel onclick", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            },
            View.OnClickListener {
                Toast.makeText(requireContext(), "OK onclick", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
        })
        dialog.show()
    }
}