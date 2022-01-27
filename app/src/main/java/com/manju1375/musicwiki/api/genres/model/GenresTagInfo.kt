package com.manju1375.musicwiki.api.genres.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenresTagInfo (
    @SerializedName("tag")
    @Expose
    var tag: Tag? = null
):Parcelable