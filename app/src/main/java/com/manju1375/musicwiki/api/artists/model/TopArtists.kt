package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopArtists (
    @SerializedName("artist")
    @Expose
    var artist: List<Artist>? = null,

    @SerializedName("@attr")
    @Expose
    var attr: AttrArtistResponse? = null

):Parcelable