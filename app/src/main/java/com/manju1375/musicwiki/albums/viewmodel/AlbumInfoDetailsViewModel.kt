package com.manju1375.musicwiki.albums.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.albums.model.AlbumInfo
import com.manju1375.musicwiki.api.albums.model.AlbumInfoDetails
import com.manju1375.musicwiki.api.albums.service.AlbumService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class AlbumInfoDetailsViewModel(private val albumService: AlbumService) : BaseViewModel() {

    val infoViewState: MutableLiveData<AlbumsInfoViewState> = MutableLiveData()
    private var fetchAlbumInfo: Boolean = false
    private val TAG = AlbumInfoDetailsViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchAlbumInfo(albumParams: List<String>?) {
        albumParams?:return
        if (fetchAlbumInfo) {
            return
        }
        Log.d(TAG,"fetching AlbumInfo...")
        fetchAlbumInfo = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap = hashMapOf("method" to "album.getInfo", "album" to albumParams[0],"artist" to albumParams[1], "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(albumService.getAlbumInfo(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ album ->
                Log.d(TAG,"fetching albumInfo:"+album)
                loaderVisibility.postValue(View.GONE)
                setUpView(album)
                fetchAlbumInfo = false
            }, { e ->
                Log.d(TAG,"fetching GenresInfo:"+e.message)
                loaderVisibility.postValue(View.GONE)
                when (albumService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchAlbumInfo = false
            })
        )
    }
    private fun setUpView(album: AlbumInfo){
        val newViewState = AlbumsInfoViewState()
        album.let { album ->
             album.album?.tags?.tag?.let {
                newViewState.tagListItems = it
            }
            album.album?.wiki?.summary?.let {
                newViewState.summary = it
            }
        }
        infoViewState.postValue(newViewState)
    }
}
