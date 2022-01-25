package com.h.androidkotlinsample.interfaces

interface BackPressedInterface {
    /**
     * true : not onbackpressed
     * false : onbackpressed
     * */
    fun onBackPressed() : Boolean
}