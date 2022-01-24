package com.h.androidkotlinsample.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("/")
    fun getCall() : Flowable<Any>

    @GET("/search/repositories")
    fun getCall2(@Query("q") q : String, @Query("sort") sort : String) : Flowable<Any>
}