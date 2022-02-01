package com.manju1375.musicwiki.artists.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.manju1375.musicwiki.api.albums.model.Albums
import com.manju1375.musicwiki.api.artists.model.ArtistInfo
import com.manju1375.musicwiki.api.artists.service.ArtistService
import com.manju1375.musicwiki.api.tracks.model.Tracks
import com.manju1375.musicwiki.common.utils.RxUtils
import com.manju1375.musicwiki.common.viewmodel.BaseViewModel
import com.manju1375.musicwiki.config.Constants
import com.manju1375.musicwiki.tracks.viewmodel.TracksViewState

class ArtistInfoDetailsViewModel(private val artistService: ArtistService) : BaseViewModel() {

    val infoViewState: MutableLiveData<ArtistInfoViewState> = MutableLiveData()
    val tracksViewState: MutableLiveData<TopTracksViewState> = MutableLiveData()
    val albumsViewState: MutableLiveData<TopAlbumsViewState> = MutableLiveData()
    private var fetchArtistInfo: Boolean = false
    private var fetchTopTracks: Boolean = false
    private var fetchTopAlbums: Boolean = false
    private val TAG = ArtistInfoDetailsViewModel::class.java.simpleName

    fun setLoaderVisibility(visible: Int) {
        loaderVisibility.postValue(visible)
    }

    fun fetchArtistInfo(artistName: String?) {
        artistName ?: return
        if (fetchArtistInfo) {
            return
        }
        Log.d(TAG, "fetching ArtistInfo...")
        fetchArtistInfo = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap =
            hashMapOf("method" to "artist.getInfo", "artist" to artistName, "api_key" to "0f408f6404a94723710b4e444a0382b4", "format" to "json")
        addDisposable(
            artistService.getArtistInfo(Constants.ENDPOINT_BASE_URL, hashMap)
                .compose(RxUtils.applySingleSchedulers())
                .subscribe({ artistInfo ->
                    Log.d(TAG, "fetching albumInfo:" + artistInfo)
                    loaderVisibility.postValue(View.GONE)
                    setUpView(artistInfo)
                    fetchArtistInfo = false
                }, { e ->
                    Log.d(TAG, "fetching GenresInfo:" + e.message)
                    loaderVisibility.postValue(View.GONE)
                    when (artistService.getErrorCode(e)) {
                        Constants.HttpError.BAD_REQUEST_ERROR -> {
                        }
                        Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                        }
                        Constants.HttpError.FORBIDDEN_ERROR -> {

                        }
                    }
                    fetchArtistInfo = false
                })
        )
    }

    fun fetchTopTracksForArtist(artistName: String?) {
        artistName ?: return
        if (fetchTopTracks) {
            return
        }
        Log.d(TAG, "fetching Top Tracks...")
        fetchTopTracks = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap =
            hashMapOf("method" to "tag.gettoptracks", "artist" to artistName, "api_key" to "0f408f6404a94723710b4e444a0382b4", "format" to "json")
        addDisposable(
            artistService.getTopTracksForArtist(Constants.ENDPOINT_BASE_URL, hashMap)
                .compose(RxUtils.applySingleSchedulers())
                .subscribe({ trackDetails ->
                    Log.d(TAG, "fetching Top Tracks:" + trackDetails)
                    loaderVisibility.postValue(View.GONE)
                    trackDetails?.tracks?.let {
                        setUpViewForTracks(it)
                    }
                    fetchTopTracks = false
                }, { e ->
                    Log.d(TAG, "fetching GenresInfo:" + e.message)
                    loaderVisibility.postValue(View.GONE)
                    when (artistService.getErrorCode(e)) {
                        Constants.HttpError.BAD_REQUEST_ERROR -> {
                        }
                        Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                        }
                        Constants.HttpError.FORBIDDEN_ERROR -> {

                        }
                    }
                    fetchTopTracks = false
                })
        )
    }

    fun fetchTopAlbumsForArtist(artistName: String?) {
        artistName ?: return
        if (fetchTopAlbums) {
            return
        }
        Log.d(TAG, "fetching Top Albums...")
        fetchTopAlbums = true
        loaderVisibility.postValue(View.VISIBLE)
        val hashMap =
            hashMapOf("method" to "tag.gettopalbums", "artist" to artistName, "api_key" to "0f408f6404a94723710b4e444a0382b4", "format" to "json")
        addDisposable(
            artistService.getTopAlbumsForArtist(Constants.ENDPOINT_BASE_URL, hashMap)
                .compose(RxUtils.applySingleSchedulers())
                .subscribe({ albumDetails ->
                    Log.d(TAG, "fetching Top Albums:" + albumDetails)
                    loaderVisibility.postValue(View.GONE)
                    albumDetails?.albums?.let {
                        setUpViewForAlbums(it)
                    }
                    fetchTopAlbums = false
                }, { e ->
                    Log.d(TAG, "fetching GenresInfo:" + e.message)
                    loaderVisibility.postValue(View.GONE)
                    when (artistService.getErrorCode(e)) {
                        Constants.HttpError.BAD_REQUEST_ERROR -> {
                        }
                        Constants.HttpError.INTERNAL_SERVER_ERROR -> {
                        }
                        Constants.HttpError.FORBIDDEN_ERROR -> {

                        }
                    }
                    fetchTopAlbums = false
                })
        )
    }

    private fun setUpView(artistInfo: ArtistInfo) {
        val newViewState = ArtistInfoViewState()
        artistInfo.let { artistInfo1 ->
            newViewState.name = artistInfo1.artist?.name
            newViewState.summary = artistInfo1.artist?.bio?.summary
            newViewState.playCount = artistInfo1.artist?.stats?.playcount
            newViewState.followers = artistInfo1.artist?.stats?.listeners
            artistInfo1.artist?.tags?.tag?.let { artistItems ->
                newViewState.tagListItems = artistItems
            }
        }
        infoViewState.postValue(newViewState)
    }

    private fun setUpViewForTracks(tracks: Tracks) {
        val newViewState = TopTracksViewState()
        tracks.let { tracksList ->
            tracksList.track?.let {
                newViewState.tagListItems = it
            }
        }
        tracksViewState.postValue(newViewState)
    }

    private fun setUpViewForAlbums(albums: Albums) {
        val newViewState = TopAlbumsViewState()
        albums.let { albumsList ->
            albumsList.album?.let {
                newViewState.tagListItems = it
            }
        }
        albumsViewState.postValue(newViewState)
    }
}
