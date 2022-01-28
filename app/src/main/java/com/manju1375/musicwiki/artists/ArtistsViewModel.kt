package com.manju1375.musicwiki.artists

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.service.ArtistService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class ArtistsViewModel(private val artistsService: ArtistService) : BaseViewModel() {

    private val artistsDetailsLiveData: MutableLiveData<ArtistsDetails> = MutableLiveData()
    private var fetchArtists: Boolean = false
    private val TAG = ArtistsViewModel::class.java.simpleName


    fun fetchArtistsForTag() {
        if (fetchArtists) {
            return
        }
        Log.d(TAG,"fetching Artists...")
        fetchArtists = true
        val hashMap =hashMapOf("method" to "tag.gettopartists", "tag" to "disco", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(artistsService.getArtists(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ artistsDetails ->
                Log.d(TAG,"fetchArtists:"+artistsDetails)
                artistsDetailsLiveData.postValue(artistsDetails)
                fetchArtists = false
            }, { e ->
                Log.d(TAG,"fetchArtists:"+e.message)
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

}
