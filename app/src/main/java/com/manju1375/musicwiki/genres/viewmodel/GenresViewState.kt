package com.manju1375.musicwiki.genres.viewmodel

import com.manju1375.musicwiki.api.genres.model.Tag

data class GenresViewState(
    var tagListItems: List<Tag> = mutableListOf(),
    var isLoadMoreItems: Boolean = true
)
