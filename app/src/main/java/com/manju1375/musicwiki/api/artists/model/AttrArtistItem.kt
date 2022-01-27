package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AttrArtistItem (
    @SerializedName("rank")
    @Expose
    var rank: String? = null

):Parcelable