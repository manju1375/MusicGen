package com.manju1375.musicwiki.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseApiRequest (
    @SerializedName("api_key")
    @Expose
    val apiKey: String? = null,

    @SerializedName("format")
    @Expose
    val format: String? = null
)