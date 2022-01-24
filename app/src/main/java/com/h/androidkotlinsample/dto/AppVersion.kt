package com.h.androidkotlinsample.dto

import java.io.Serializable

data class AppVersion(var version: String? = null,
                      val msg: String? = null,
                      val update: String? = null,
                      val path: String? = null,
                      val domain: String? = null,
                      val splashPath: String?? = null
) : Serializable

