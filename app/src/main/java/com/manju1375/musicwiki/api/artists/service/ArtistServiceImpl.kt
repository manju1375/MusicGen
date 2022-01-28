package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.config.Constants
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit

class ArtistServiceImpl(retrofit: Retrofit):ArtistService {
    private val artistService = retrofit.create(ArtistService::class.java)

    override fun getArtists(url: String, hashMap: HashMap<String, String>): Single<ArtistsDetails> {
        return artistService.getArtists(url,hashMap)
    }

    override fun getErrorCode(e: Throwable?): Int {
        if (e is HttpException) {
            return e.code()
        }
        //Return general error code
        return Constants.HttpError.INTERNAL_SERVER_ERROR
    }
}