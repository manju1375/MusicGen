package com.manju1375.musicwiki.tracks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import com.manju1375.musicwiki.api.tracks.service.TrackService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class TracksViewModel(private val trackService: TrackService) : BaseViewModel() {

    private val trackDetailsLiveData: MutableLiveData<TracksDetails> = MutableLiveData()
    private var fetchTracks: Boolean = false
    private val TAG = TracksViewModel::class.java.simpleName


    fun fetchTracksForTag() {
        if (fetchTracks) {
            return
        }
        Log.d(TAG,"fetching Tracks...")
        fetchTracks = true
        val hashMap =hashMapOf("method" to "tag.gettoptracks", "tag" to "disco", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(trackService.getTracks(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ tracksDetails ->
                Log.d(TAG,"fetchTracks:"+tracksDetails)
                trackDetailsLiveData.postValue(tracksDetails)
                fetchTracks = false
            }, { e ->
                Log.d(TAG,"fetchTracks:"+e.message)
                when (trackService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchTracks = false
            })
        )
    }

}
