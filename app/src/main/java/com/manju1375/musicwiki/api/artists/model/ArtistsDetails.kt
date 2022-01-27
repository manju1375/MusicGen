package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistsDetails (
    @SerializedName("topartists")
    @Expose
    var topartists: TopArtists? = null
):Parcelable