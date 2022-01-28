package com.manju1375.musicwiki.genres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manju1375.musicwiki.api.MusicWikiServices

class GenresViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(
            when (modelClass) {
                GenresViewModel::class.java ->{
                    GenresViewModel(MusicWikiServices.musicWikiApi.genreService)
                }
                else -> {
                    val illegalArgumentException =
                        IllegalArgumentException("Unknown ViewModel class")
                    throw illegalArgumentException
                }
        })!!
    }
}
