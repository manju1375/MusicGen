package com.manju1375.musicwiki.api.artists.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttrArtistResponse (
    @SerializedName("tag")
    @Expose
    var tag: String? = null,

    @SerializedName("page")
    @Expose
    var page: String? = null,

    @SerializedName("perPage")
    @Expose
    var perPage: String? = null,

    @SerializedName("totalPages")
    @Expose
    var totalPages: String? = null,

    @SerializedName("total")
    @Expose
    var total: String? = null
):Parcelable