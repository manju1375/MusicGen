package com.manju1375.musicwiki.tracks.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import com.manju1375.musicwiki.api.tracks.service.TrackService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class TracksViewModel(private val trackService: TrackService) : BaseViewModel() {

    val tracksViewState: MutableLiveData<TracksViewState> = MutableLiveData()
    private var fetchTracks: Boolean = false
    private val TAG = TracksViewModel::class.java.simpleName


    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchTracksForTag(selectedGenre: String?) {
        selectedGenre?:return
        if (fetchTracks) {
            return
        }
        Log.d(TAG,"fetching Tracks...")
        fetchTracks = true
        val hashMap =hashMapOf("method" to "tag.gettoptracks", "tag" to selectedGenre, "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(trackService.getTracks(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ tracksDetails ->
                Log.d(TAG,"fetchTracks:"+tracksDetails)
                loaderVisibility.postValue(View.GONE)
                setUpView(tracksDetails)
                fetchTracks = false
            }, { e ->
                Log.d(TAG,"fetchTracks:"+e.message)
                loaderVisibility.postValue(View.GONE)
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
    private fun setUpView(trackDetails: TracksDetails){
        val newViewState = TracksViewState()
        trackDetails.tracks?.track?.let { tracksList ->
            newViewState.tracksListItems = tracksList
        }
        tracksViewState.postValue(newViewState)
    }


}
