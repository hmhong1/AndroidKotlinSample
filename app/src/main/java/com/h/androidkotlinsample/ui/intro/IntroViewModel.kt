package com.h.androidkotlinsample.ui.intro

import android.app.Application
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.h.androidkotlinsample.repository.ApiRepository
import com.h.androidkotlinsample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(var apiRepository: ApiRepository, application: Application) : BaseViewModel(application) {

    var textValue = MutableLiveData<String>("")

    init {
        Handler().postDelayed(Runnable {
            requestStart()
        }, 3 * 1000)
    }

    private fun requestStart() {
        isProgressDialogLiveData.postValue(true)

        val apiVersion = apiRepository.getCall2("dd", "stars")

        addToDisposable(
            apiVersion.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ v ->
                    // 성공
                    isProgressDialogLiveData.postValue(false)

                    textValue.value = "성공"

                }, {
                    isProgressDialogLiveData.postValue(false)

                    textValue.value = "실패"
                }))

    }
}
