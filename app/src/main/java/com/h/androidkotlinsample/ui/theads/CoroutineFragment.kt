package com.h.androidkotlinsample.ui.theads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.FragmentCoroutineBinding
import com.h.androidkotlinsample.databinding.MainActionbarLayoutBinding
import com.h.androidkotlinsample.ui.base.BaseFragment
import com.h.androidkotlinsample.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class CoroutineFragment : BaseFragment() {

    private var mContext : Context? = null

    private lateinit var mainActionbarBinding : MainActionbarLayoutBinding
    private lateinit var binding : FragmentCoroutineBinding
    private val viewModel by viewModels<CoroutineViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coroutine, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this@CoroutineFragment

        mainActionbarBinding = binding.mainActionbarLayout
        mainActionbarBinding.activity = activity as MainActivity

        mContext = activity

        progressDialogInit(viewModel!!)
        init()
        observers()

        return binding.root
    }

    private fun init() {

    }

    private fun observers() {

    }

    private suspend fun setTextOnMainThread(input : String) {
        withContext(Dispatchers.Main) {
            binding.txResult.text = input
        }
    }

    fun onClickBase() {
//        GlobalScope.launch {
//            setTextOnMainThread("abcabcabc")
//        }

//        lifecycleScope.launch {
//            //androidx의 lifecycle 의존성을 추가하는 부분
//            setTextOnMainThread("abcabcabc123123")
//        }

        GlobalScope.launch {
            val name = withContext(Dispatchers.IO) {
                "leveloper"
            }

            setTextOnMainThread("name : $name")
        }

//        GlobalScope.launch {
//            var job = Job()
//
//            CoroutineScope(Dispatchers.Default + job).launch {
//                launch {
//                    println("coroutinel1 start")
//                    delay(1000)
//                    println("coroutinel1 end")
//                }
//                launch {
//                    println("coroutinel2 start")
//                    delay(1000)
//                    println("coroutinel2 end")
//                }
//            }
//            delay(300)
//            job.cancel()
//            delay(1000)
//            println("all done!!")
//        }
    }
}