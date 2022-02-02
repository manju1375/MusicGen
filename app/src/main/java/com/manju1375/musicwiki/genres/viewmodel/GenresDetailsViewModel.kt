package com.manju1375.musicwiki.genres.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.BuildConfig
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.utils.getHtmlText
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class GenresDetailsViewModel(private val genreService: GenreService) : BaseViewModel() {

    val genreInfoViewState: MutableLiveData<GenresInfoViewState> = MutableLiveData()
    private var fetchGenresInfo: Boolean = false
    private val TAG = GenresDetailsViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchGenresInfo(selectedGenre: String?) {
        selectedGenre?:return
        if (fetchGenresInfo) {
            return
        }
        Log.d(TAG,"fetching GenresInfo...")
        fetchGenresInfo = true
        val hashMap =hashMapOf("method" to "tag.getInfo", "tag" to selectedGenre,  "api_key" to BuildConfig.CONSUMER_KEY,"format" to "json")
        addDisposable(genreService.getGenreInfo(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ genresTagInfo ->
                Log.d(TAG,"fetching GenresInfo:"+genresTagInfo)
                loaderVisibility.postValue(View.GONE)
                setUpView(genresTagInfo)
                fetchGenresInfo = false
            }, { e ->
                Log.d(TAG,"fetching GenresInfo:"+e.message)
                loaderVisibility.postValue(View.GONE)
                when (genreService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchGenresInfo = false
            })
        )
    }
    private fun setUpView(genresTagInfo: GenresTagInfo){
        val newViewState = GenresInfoViewState()
        genresTagInfo.tag?.let { genreInfo ->
            newViewState.genreInfoName = genreInfo.name
            newViewState.genreInfoSummary = getHtmlText(genreInfo.wiki?.summary)
        }
        genreInfoViewState.postValue(newViewState)
    }
}
