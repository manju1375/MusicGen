package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ArtistService {
    @GET()
    fun getArtists(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<ArtistsDetails>
    fun getErrorCode(e: Throwable?): Int
}