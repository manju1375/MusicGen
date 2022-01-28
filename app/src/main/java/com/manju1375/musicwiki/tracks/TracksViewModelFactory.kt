package com.manju1375.musicwiki.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manju1375.musicwiki.api.MusicWikiServices

class TracksViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(
            when (modelClass) {
                TracksViewModel::class.java ->{
                    TracksViewModel(MusicWikiServices.musicWikiApi.trackService)
                }
                else -> {
                    val illegalArgumentException =
                        IllegalArgumentException("Unknown ViewModel class")
                    throw illegalArgumentException
                }
        })!!
    }
}
