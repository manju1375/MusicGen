package com.manju1375.musicwiki.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manju1375.musicwiki.api.MusicWikiServices

class AlbumsViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(
            when (modelClass) {
                AlbumsViewModel::class.java ->{
                    AlbumsViewModel(MusicWikiServices.musicWikiApi.albumService)
                }
                else -> {
                    val illegalArgumentException =
                        IllegalArgumentException("Unknown ViewModel class")
                    throw illegalArgumentException
                }
        })!!
    }
}
