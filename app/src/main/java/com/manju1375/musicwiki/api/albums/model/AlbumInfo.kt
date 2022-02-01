package com.manju1375.musicwiki.api.albums.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AlbumInfo  (
    @SerializedName("album")
    @Expose
    var album: AlbumInfoDetails? = null
):Parcelable