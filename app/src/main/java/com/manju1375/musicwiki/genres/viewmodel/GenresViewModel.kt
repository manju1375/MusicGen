package com.manju1375.musicwiki.genres.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import com.manju1375.musicwiki.api.genres.request.GenresRequest
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class GenresViewModel(private val genreService: GenreService) : BaseViewModel() {

    private val genresDetailsLiveData: MutableLiveData<GenresTagDetails> = MutableLiveData()
    private val genresTagInfoLiveData: MutableLiveData<GenresTagInfo> = MutableLiveData()
    private var fetchGenres: Boolean = false
    private var fetchGenresInfo: Boolean = false
    private val TAG = GenresViewModel::class.java.simpleName


    fun fetchGenres() {
        if (fetchGenres) {
            return
        }
        Log.d(TAG,"fetching Genres...")
        fetchGenres = true
        val hashMap =hashMapOf("method" to "chart.getTopTags", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(genreService.getGenres(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ genresDetails ->
                Log.d(TAG,"fetchGenres:"+genresDetails)
                genresDetailsLiveData.postValue(genresDetails)
                fetchGenres = false
            }, { e ->
                Log.d(TAG,"fetchGenres:"+e.message)
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
}
