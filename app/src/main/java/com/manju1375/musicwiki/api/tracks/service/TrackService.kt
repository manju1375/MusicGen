package com.manju1375.musicwiki.api.tracks.service

import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface TrackService {
    @GET()
    fun getTracks(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<TracksDetails>
    fun getErrorCode(e: Throwable?): Int
}