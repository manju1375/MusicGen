package com.manju1375.musicwiki.api.albums.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.artists.model.Artist
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("mbid")
    @Expose
    var mbid: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("artist")
    @Expose
    var artist: Artist? = null,

    @SerializedName("image")
    @Expose
    var image: List<Image>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: AttrAlbum? = null

):Parcelable