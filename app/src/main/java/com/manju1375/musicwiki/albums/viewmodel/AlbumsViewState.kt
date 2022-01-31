package com.manju1375.musicwiki.albums.viewmodel

import com.manju1375.musicwiki.api.albums.model.Album

data class AlbumsViewState(
    var albumListItems: List<Album> = mutableListOf(),
)
