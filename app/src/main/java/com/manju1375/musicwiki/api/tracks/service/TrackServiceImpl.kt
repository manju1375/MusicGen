package com.manju1375.musicwiki.api.tracks.service

import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import com.manju1375.musicwiki.config.Constants
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.QueryMap
import retrofit2.http.Url

class TrackServiceImpl(retrofit: Retrofit):TrackService {
    private val trackService = retrofit.create(TrackService::class.java)
    override fun getTracks(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<TracksDetails> {
        return trackService.getTracks(url,hashMap)
    }
    override fun getErrorCode(e: Throwable?): Int {
        if (e is HttpException) {
            return e.code()
        }
        //Return general error code
        return Constants.HttpError.INTERNAL_SERVER_ERROR
    }

}