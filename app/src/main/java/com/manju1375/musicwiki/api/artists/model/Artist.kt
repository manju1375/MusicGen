package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("mbid")
    @Expose
    var mbid: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("streamable")
    @Expose
    var streamable: String? = null,

    @SerializedName("image")
    @Expose
    var image: List<Image>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: AttrArtistItem? = null

):Parcelable