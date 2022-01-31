package com.manju1375.musicwiki.artists.viewmodel

import com.manju1375.musicwiki.api.artists.model.Artist

data class ArtistsViewState(
    var artistListItems: List<Artist> = mutableListOf(),
)
