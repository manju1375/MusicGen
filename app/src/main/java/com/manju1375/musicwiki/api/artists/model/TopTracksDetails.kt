package com.manju1375.musicwiki.api.artists.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.tracks.model.Tracks

data class TopTracksDetails  (
    @SerializedName("tracks")
    @Expose
    var tracks: Tracks? = null

)