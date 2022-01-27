package com.manju1375.musicwiki.api.albums.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Albums (
    @SerializedName("album")
    @Expose
    var album: List<Album>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: AttrAlbumResponse? = null

):Parcelable