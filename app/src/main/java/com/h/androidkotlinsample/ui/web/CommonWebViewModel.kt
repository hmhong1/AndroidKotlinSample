package com.h.androidkotlinsample.ui.web

import android.app.Application
import com.h.androidkotlinsample.repository.ApiRepository
import com.h.androidkotlinsample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommonWebViewModel @Inject constructor(var apiRepository: ApiRepository, application: Application) : BaseViewModel(application) {
}