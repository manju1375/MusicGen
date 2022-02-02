package com.manju1375.musicwiki.genres.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.BuildConfig
import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import com.manju1375.musicwiki.api.genres.request.GenresRequest
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class GenresViewModel(private val genreService: GenreService) : BaseViewModel() {

    val genresTagInfoLiveData: MutableLiveData<GenresTagInfo> = MutableLiveData()
    val genresViewState: MutableLiveData<GenresViewState> = MutableLiveData()
    private var fetchGenres: Boolean = false
    private var fetchGenresInfo: Boolean = false
    private val TAG = GenresViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchGenres() {
        if (fetchGenres) {
            return
        }
        Log.d(TAG,"fetching Genres...")
        fetchGenres = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap =hashMapOf("method" to "chart.getTopTags", "api_key" to BuildConfig.CONSUMER_KEY,"format" to "json")
        addDisposable(genreService.getGenres(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ genresDetails ->
                Log.d(TAG,"fetchGenres:"+genresDetails)
                loaderVisibility.postValue(View.GONE)
                setUpView(genresDetails)
                fetchGenres = false
            }, { e ->
                Log.d(TAG,"fetchGenres:"+e.message)
                loaderVisibility.postValue(View.GONE)
                when (genreService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchGenres = false
            })
        )
    }

    fun fetchGenresInfo() {
        if (fetchGenresInfo) {
            return
        }
        Log.d(TAG,"fetching GenresInfo...")
        fetchGenresInfo = true
        val hashMap =hashMapOf("method" to "tag.getInfo", "tag" to "rock", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(genreService.getGenreInfo(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ genresTagInfo ->
                Log.d(TAG,"fetching GenresInfo:"+genresTagInfo)
                genresTagInfoLiveData.postValue(genresTagInfo)
                fetchGenres = false
            }, { e ->
                Log.d(TAG,"fetching GenresInfo:"+e.message)
                when (genreService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchGenres = false
            })
        )
    }
    private fun setUpView(genresTagDetails: GenresTagDetails){
        val newViewState = GenresViewState()
        genresTagDetails.tags?.tag?.let { genresList ->
            newViewState.tagListItems = genresList
        }
        genresViewState.postValue(newViewState)
    }
}
