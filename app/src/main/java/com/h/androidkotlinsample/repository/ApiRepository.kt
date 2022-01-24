package com.h.androidkotlinsample.repository

import com.h.androidkotlinsample.network.ApiServiceInterface
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiServiceInterface) {

    fun getCall() = apiService.getCall()
    fun getCall2(q : String, sort : String) = apiService.getCall2(q, sort)
}