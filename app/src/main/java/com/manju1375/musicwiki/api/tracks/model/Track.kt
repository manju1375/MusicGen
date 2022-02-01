package com.manju1375.musicwiki.api.tracks.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.artists.model.Artist
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("duration")
    @Expose
    var duration: String? = null,

    @SerializedName("mbid")
    @Expose
    var mbid: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("streamable")
    @Expose
    var streamable: Streamable? = null,

    @SerializedName("artist")
    var artist: Artist? = null,

    @SerializedName("image")
    @Expose
    var image: List<Image>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: AttrTrackItem? = null

):Parcelable