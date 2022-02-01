package com.manju1375.musicwiki.artists.viewmodel

import com.manju1375.musicwiki.api.albums.model.Album
import com.manju1375.musicwiki.api.genres.model.Tag

data class TopAlbumsViewState(
    var tagListItems: List<Album> = mutableListOf(),
)
