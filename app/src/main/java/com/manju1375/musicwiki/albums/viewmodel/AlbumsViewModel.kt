package com.manju1375.musicwiki.albums.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.BuildConfig
import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.service.AlbumService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class AlbumsViewModel(private val albumService: AlbumService) : BaseViewModel() {

    val albumsViewState: MutableLiveData<AlbumsViewState> = MutableLiveData()
    private var fetchAlbums: Boolean = false
    private val TAG = AlbumsViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchAlbumsForTag(selectedGenre: String?) {
        selectedGenre?:return
        if (fetchAlbums) {
            return
        }
        Log.d(TAG,"fetching Albums...")
        fetchAlbums = true
        val hashMap =hashMapOf("method" to "tag.gettopalbums", "api_key" to BuildConfig.CONSUMER_KEY,"tag" to selectedGenre, "format" to "json")
        addDisposable(albumService.getAlbums(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ albumDetails ->
                Log.d(TAG,"fetchAlbums:"+albumDetails)
                loaderVisibility.postValue(View.GONE)
                setUpView(albumDetails)
                fetchAlbums = false
            }, { e ->
                Log.d(TAG,"fetchAlbums:"+e.message)
                loaderVisibility.postValue(View.GONE)
                when (albumService.getErrorCode(e)) {
                    Constants.HttpError.BAD_REQUEST_ERROR -> {
                    }
                    Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                    }
                    Constants.HttpError.FORBIDDEN_ERROR -> {

                    }
                }
                fetchAlbums = false
            })
        )
    }

    private fun setUpView(albumDetails: AlbumDetails){
        val newViewState = AlbumsViewState()
        albumDetails.albums?.album?.let { albumItems ->
            newViewState.albumListItems = albumItems
        }
        albumsViewState.postValue(newViewState)
    }

}
