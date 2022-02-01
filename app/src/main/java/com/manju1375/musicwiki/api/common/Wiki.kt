package com.manju1375.musicwiki.api.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wiki (
    @SerializedName("published")
    @Expose
    var published: String? = null,

    @SerializedName("summary")
    @Expose
    var summary: String? = null,

    @SerializedName("content")
    @Expose
    var content: String? = null

):Parcelable