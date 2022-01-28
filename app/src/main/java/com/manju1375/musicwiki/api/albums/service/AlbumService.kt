package com.manju1375.musicwiki.api.albums.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.request.AlbumsRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface AlbumService {
    @GET()
    fun getAlbums(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<AlbumDetails>
    fun getErrorCode(e: Throwable?): Int
}