package com.manju1375.musicwiki.api.albums.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.request.AlbumsRequest
import io.reactivex.Single
import retrofit2.http.Body

interface AlbumService {
    fun getAlbums(@Body albumsRequest: AlbumsRequest): Single<AlbumDetails>
}