package com.manju1375.musicwiki.artists.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.BuildConfig
import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.service.ArtistService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class ArtistsViewModel(private val artistsService: ArtistService) : BaseViewModel() {

    val artistsViewState: MutableLiveData<ArtistsViewState> = MutableLiveData()
    private var fetchArtists: Boolean = false
    private val TAG = ArtistsViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchArtists(selectedGenre: String?) {
        selectedGenre?:return
        if (fetchArtists) {
            return
        }
        Log.d(TAG,"fetching Genres...")
        fetchArtists = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap =hashMapOf("method" to "tag.gettopartists", "api_key" to BuildConfig.CONSUMER_KEY,"tag" to selectedGenre, "format" to "json")
        addDisposable(artistsService.getArtists(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ artistsDetails ->
                Log.d(TAG,"fetchArtists:"+artistsDetails)
                loaderVisibility.postValue(View.GONE)
                setUpView(artistsDetails)
                fetchArtists = false
            }, { e ->
                Log.d(TAG,"fetchArtists:"+e.message)
                loaderVisibility.postValue(View.GONE)
                when (artistsService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchArtists = false
            })
        )
    }


    private fun setUpView(artistsDetails: ArtistsDetails){
        val newViewState = ArtistsViewState()
        artistsDetails.topartists?.artist?.let { artistsList ->
            newViewState.artistListItems = artistsList
        }
        artistsViewState.postValue(newViewState)
    }
}
