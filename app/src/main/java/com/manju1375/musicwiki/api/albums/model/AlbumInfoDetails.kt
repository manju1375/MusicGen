package com.manju1375.musicwiki.api.albums.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.common.Wiki
import com.manju1375.musicwiki.api.genres.model.Tags
import com.manju1375.musicwiki.api.tracks.model.Tracks
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumInfoDetails (
    @SerializedName("artist")
    @Expose
    var artist: String? = null,

    @SerializedName("mbid")
    var mbid: String? = null,

    @SerializedName("tags")
    @Expose
    var tags: Tags? = null,

    @SerializedName("playcount")
    var playcount: String? = null,

    @SerializedName("image")
    @Expose
    var image: List<Image>? = null,

    @SerializedName("tracks")
    var tracks: Tracks? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("name")
    @Transient
    var name: String? = null,

    @SerializedName("listeners")
    var listeners: String? = null,

    @SerializedName("wiki")
    @Expose
    var wiki: Wiki? = null
):Parcelable