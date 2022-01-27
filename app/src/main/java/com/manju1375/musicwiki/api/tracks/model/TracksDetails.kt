package com.manju1375.musicwiki.api.tracks.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TracksDetails (
    @SerializedName("tracks")
    @Expose
    var tracks: Tracks? = null
):Parcelable