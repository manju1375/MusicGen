package com.manju1375.musicwiki.api.albums.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumDetails(
    @SerializedName("albums")
    @Expose
    var albums: Albums? = null

):Parcelable