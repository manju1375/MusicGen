package com.manju1375.musicwiki.api.genres.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Wiki (
    @SerializedName("summary")
    @Expose
    var summary: String? = null,

    @SerializedName("content")
    @Expose
    var content: String? = null
):Parcelable