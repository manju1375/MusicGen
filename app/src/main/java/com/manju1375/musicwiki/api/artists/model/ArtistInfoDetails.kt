package com.manju1375.musicwiki.api.artists.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.genres.model.Tags


data class ArtistInfoDetails (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("mbid")
    var mbid: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("image")
    var image: List<Image>? = null,

    @SerializedName("stats")
    @Expose
    var stats: ArtistInfoStats? = null,

    @SerializedName("streamable")
    var streamable: String? = null,

    @SerializedName("ontour")
    var ontour: String? = null,

    @SerializedName("tags")
    @Expose
    var tags: Tags? = null,

    @SerializedName("bio")
    @Expose
    var bio: ArtistBio? = null
)