package com.manju1375.musicwiki.api.tracks.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tracks (
    @SerializedName("track")
    @Expose
    var track: List<Track>? = null,

    @SerializedName("@attr")
    var attr: AttrTracksResponse? = null

):Parcelable
