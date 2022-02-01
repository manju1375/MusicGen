package com.manju1375.musicwiki.artists.viewmodel

import com.manju1375.musicwiki.api.genres.model.Tag
import com.manju1375.musicwiki.api.tracks.model.Track

data class TopTracksViewState(
    var tagListItems: List<Track> = mutableListOf(),
)
