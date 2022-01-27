package com.manju1375.musicwiki.api.albums.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.request.AlbumsRequest
import io.reactivex.Single
import retrofit2.Retrofit

class AlbumServiceImpl(retrofit: Retrofit):AlbumService {

    override fun getAlbums(albumsRequest: AlbumsRequest): Single<AlbumDetails> {
        TODO("Not yet implemented")
    }
}