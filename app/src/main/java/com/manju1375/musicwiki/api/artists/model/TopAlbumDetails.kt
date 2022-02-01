package com.manju1375.musicwiki.api.artists.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.manju1375.musicwiki.api.albums.model.Album
import com.manju1375.musicwiki.api.albums.model.Albums
import com.manju1375.musicwiki.api.tracks.model.Tracks

data class TopAlbumDetails  (
    @SerializedName("album")
    @Expose
    var albums: Albums? = null

)