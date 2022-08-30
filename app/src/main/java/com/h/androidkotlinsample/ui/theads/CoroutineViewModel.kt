package com.h.androidkotlinsample.ui.theads

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.h.androidkotlinsample.repository.ApiRepository
import com.h.androidkotlinsample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CoroutineViewModel @Inject constructor(var apiRepository: ApiRepository, application: Application) : BaseViewModel(application) {

    init {
    }
}
