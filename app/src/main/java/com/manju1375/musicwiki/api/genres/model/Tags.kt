package com.manju1375.musicwiki.api.genres.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Tags(
    @SerializedName("tag")
    @Expose
    var tag: List<Tag>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: Attr? = null
):Parcelable