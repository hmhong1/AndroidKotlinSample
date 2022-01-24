package com.h.androidkotlinsample.ui.main

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
class MainFragViewModel @Inject constructor(var apiRepository: ApiRepository, application: Application) : BaseViewModel(application) {

    var textValue = MutableLiveData<String>("")

    init {
        requestStart()
//        mainddd()
    }

    fun test() : Flow<Int> = flow {
        for(i in 1..10) {
//            delay(100)
            emit(i)
        }
    }

    fun performRequest(request : Int): String {
        return "response $request"
    }

    fun mainddd() = runBlocking {
        launch {
            for (i in 1..5) {
                Log.v("hhm", "I'm not blocked $i")
//                delay(100)
            }
        }
//        test().collect { value -> Log.v("hhm", "$value") }
        test().collect { value -> Log.v("hhm", "$value") }

        var list = listOf<Int>(11, 22, 33)
        list.asFlow()
//            .map { request -> performRequest(request) }
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }
            .collect { value -> Log.v("hhm", "asflow $value") }
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