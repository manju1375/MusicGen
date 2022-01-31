package com.manju1375.musicwiki.tracks.viewmodel

import com.manju1375.musicwiki.api.tracks.model.Track

data class TracksViewState(
    var tracksListItems: List<Track> = mutableListOf(),
)
