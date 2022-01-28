package com.manju1375.musicwiki.api.albums.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.request.AlbumsRequest
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.config.Constants
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit

class AlbumServiceImpl(retrofit: Retrofit):AlbumService {

    private val albumService = retrofit.create(AlbumService::class.java)

    override fun getAlbums(url: String, hashMap: HashMap<String, String>): Single<AlbumDetails> {
        return albumService.getAlbums(url,hashMap)
    }

    override fun getErrorCode(e: Throwable?): Int {
        if (e is HttpException) {
            return e.code()
        }
        //Return general error code
        return Constants.HttpError.INTERNAL_SERVER_ERROR
    }
}