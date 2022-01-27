package com.manju1375.musicwiki.api.artists.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.BaseApiRequest

data class ArtistsRequest (
    @SerializedName("method")
    @Expose
    var method: String? = null,

    @SerializedName("tag")
    @Expose
    var tag: String? = null
):BaseApiRequest("0f408f6404a94723710b4e444a0382b4","json")