package com.manju1375.musicwiki.api.genres.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("reach")
    @Expose
    var reach: String? = null,

    @SerializedName("taggings")
    @Expose
    var taggings: String? = null,

    @SerializedName("streamable")
    @Expose
    var streamable: String? = null,

    @SerializedName("wiki")
    @Expose
    var wiki: Wiki? = null
):Parcelable