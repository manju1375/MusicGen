package com.manju1375.musicwiki.genres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manju1375.musicwiki.api.MusicWikiServices
import com.manju1375.musicwiki.artists.viewmodel.ArtistsViewModel

class ArtistsViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(
            when (modelClass) {
                ArtistsViewModel::class.java ->{
                    ArtistsViewModel(MusicWikiServices.musicWikiApi.artistService)
                }
                else -> {
                    val illegalArgumentException =
                        IllegalArgumentException("Unknown ViewModel class")
                    throw illegalArgumentException
                }
        })!!
    }
}
