package com.manju1375.musicwiki.api.tracks.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Streamable (
    @SerializedName("#text")
    @Expose
    var text: String? = null,

    @SerializedName("fulltrack")
    @Expose
    var fulltrack: String? = null
):Parcelable