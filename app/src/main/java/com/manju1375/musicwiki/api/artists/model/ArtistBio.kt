package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistBio(

    @SerializedName("published")
    var published: String? = null,

    @SerializedName("summary")
    @Expose
    var summary: String? = null,

    @SerializedName("content")
    var content: String? = null

):Parcelable