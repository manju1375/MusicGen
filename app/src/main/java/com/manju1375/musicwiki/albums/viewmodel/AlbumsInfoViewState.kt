package com.manju1375.musicwiki.albums.viewmodel

import com.manju1375.musicwiki.api.genres.model.Tag

data class AlbumsInfoViewState(
    var tagListItems: List<Tag> = mutableListOf(),
    var summary: String? = null
)
