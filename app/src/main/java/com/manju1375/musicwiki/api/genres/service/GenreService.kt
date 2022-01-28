package com.manju1375.musicwiki.api.genres.service

import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface GenreService {
    @GET()
    fun getGenres(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<GenresTagDetails>
    @GET()
    fun getGenreInfo(@Url url:String, @QueryMap hashMap:HashMap<String, String>):Single<GenresTagInfo>
    fun getErrorCode(e: Throwable?): Int
}