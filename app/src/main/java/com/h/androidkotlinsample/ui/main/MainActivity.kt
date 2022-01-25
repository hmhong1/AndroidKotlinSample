package com.h.androidkotlinsample.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.h.androidkotlinsample.R
import com.h.androidkotlinsample.databinding.ActivityMainBinding
import com.h.androidkotlinsample.databinding.DrawerMenuLayoutBinding
import com.h.androidkotlinsample.interfaces.BackPressedInterface
import com.h.androidkotlinsample.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerBinding: DrawerMenuLayoutBinding
    private var viewModel: MainViewModel? = null

    private var backTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        drawerBinding = binding.drawerMenuLayout
        drawerBinding.activity = this@MainActivity

        progressDialogInit(viewModel!!)

        init()
        liveData()
    }

    private fun init() {
        binding.drawerLayout?.let {
            it.apply {
                setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//                addDrawerListener(drawerListener)
            }
        }

        replaceFragment(MainFragment())
    }

    open fun replaceFragment(fragment: Fragment, bundle: Bundle? = null, tag: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if(bundle != null) fragment.arguments = bundle
        if (!tag.isNullOrEmpty()) fragmentTransaction.addToBackStack(tag)

        fragmentTransaction.replace(R.id.fragmentLayout, fragment).commitAllowingStateLoss()
    }

    private fun liveData() {
    }

    override fun onBackPressed() {
        if (binding.drawerLayout != null && binding.drawerLayout.isDrawerOpen(binding.navigationView)) {
            binding.drawerLayout.closeDrawer(binding.navigationView)
        } else {
            if(backInterfaceCheck()) {
                backPressed()
            }
        }
    }

    fun backInterfaceCheck() : Boolean {
        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentLayout)

        if (fragment !is BackPressedInterface || !(fragment as BackPressedInterface).onBackPressed()) {
            if (backStackCount() == 0) {
                return true
            }
        }
        return false
    }

    /**
     * framgnet back stack
     * */
    private fun backStackCount() : Int {
        var count = supportFragmentManager.backStackEntryCount

        if (count != 0) {
            supportFragmentManager.popBackStack()
        }

        return count
    }

    private fun backPressed() {
        var currentTime = System.currentTimeMillis()
        var gapTime = currentTime - backTime

        if (gapTime in 1..2000) {
            super.onBackPressed()
        } else {
            backTime = currentTime
            Toast.makeText(this@MainActivity, getString(R.string.back_msg), Toast.LENGTH_SHORT)
                .show()
        }
    }

//    /**
//     * 사이드메뉴
//     * */
//    private val drawerListener = object : DrawerLayout.DrawerListener {
//        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//            //슬라이드했을떄
//        }
//
//        override fun onDrawerOpened(drawerView: View) {
//            //오픈됐을때
//        }
//
//        override fun onDrawerClosed(drawerView: View) {
//            //닫혔을떄
//        }
//
//        override fun onDrawerStateChanged(newState: Int) {
//            //드로워상태가 변경될때
//        }
//    }

    /**
     * 메뉴 열기
     * */
    fun drawerOpen() {
        binding.drawerLayout?.let {
            if (!it.isDrawerOpen(binding.navigationView))
                it.openDrawer(binding.navigationView)
        }
    }

    /**
     * 메뉴 닫기
     * */
    fun drawerClose() {
        binding.drawerLayout?.let {
            if (it.isDrawerOpen(binding.navigationView))
                it.closeDrawer(binding.navigationView)
        }
    }
}