package com.manju1375.musicwiki.albums

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.service.AlbumService
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants

class AlbumsViewModel(private val albumService: AlbumService) : BaseViewModel() {

    private val albumDetailsLiveData: MutableLiveData<AlbumDetails> = MutableLiveData()
    private var fetchAlbums: Boolean = false
    private val TAG = AlbumsViewModel::class.java.simpleName


    fun fetchAlbumsForTag() {
        if (fetchAlbums) {
            return
        }
        Log.d(TAG,"fetching Albums...")
        fetchAlbums = true
        val hashMap =hashMapOf("method" to "tag.gettopalbums", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        addDisposable(albumService.getAlbums(Constants.ENDPOINT_BASE_URL,hashMap)
            .compose(RxUtils.applySingleSchedulers())
            .subscribe({ albumDetails ->
                Log.d(TAG,"fetchAlbums:"+albumDetails)
                albumDetailsLiveData.postValue(albumDetails)
                fetchAlbums = false
            }, { e ->
                Log.d(TAG,"fetchGenres:"+e.message)
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

}
