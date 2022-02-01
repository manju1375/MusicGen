package com.manju1375.musicwiki.api.artists.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class ArtistInfo  (
    @SerializedName("artist")
    @Expose
    var artist: ArtistInfoDetails? = null
)