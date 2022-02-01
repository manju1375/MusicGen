package com.manju1375.musicwiki.artists.viewmodel

import com.manju1375.musicwiki.api.genres.model.Tag

data class ArtistInfoViewState(
    var name: String? = null,
    var tagListItems: List<Tag> = mutableListOf(),
    var summary: String? = null,
    var playCount: String? = null,
    var followers: String? = null
)
