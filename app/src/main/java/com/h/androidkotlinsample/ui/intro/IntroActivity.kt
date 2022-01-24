package com.h.androidkotlinsample.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.ActivityIntroBinding
import com.h.androidkotlinsample.ui.base.BaseActivity
import com.h.androidkotlinsample.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity() {

    private lateinit var binding : ActivityIntroBinding
    private var viewModel: IntroViewModel? = null

    private var background : ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[IntroViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        liveData()

        var anim = AlphaAnimation(0f, 1f)
        anim.duration = 1 * 1000
        anim.repeatCount = Animation.INFINITE
        anim.repeatMode = Animation.REVERSE

        background = findViewById(R.id.background)
        background!!.startAnimation(anim)
    }

    private fun liveData() {
        progressDialogInit(viewModel!!)

        viewModel!!.textValue.observe(this, Observer<String> {
            if(!it.isNullOrEmpty()) {
                background!!.clearAnimation()
                moveActivity(Intent(this@IntroActivity, MainActivity::class.java))
                finish()
            }
        })
    }
}