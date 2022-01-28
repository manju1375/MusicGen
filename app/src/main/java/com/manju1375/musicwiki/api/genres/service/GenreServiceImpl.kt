package com.manju1375.musicwiki.api.genres.service

import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import com.manju1375.musicwiki.config.Constants
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit

class GenreServiceImpl(retrofit: Retrofit):GenreService {

    private val genreService = retrofit.create(GenreService::class.java)

    override fun getGenres(url: String, hashMap: HashMap<String, String>): Single<GenresTagDetails> {
        return genreService.getGenres(url,hashMap)
    }

    override fun getGenreInfo(url: String, hashMap: HashMap<String, String>): Single<GenresTagInfo> {
        return genreService.getGenreInfo(url,hashMap)
    }

    override fun getErrorCode(e: Throwable?): Int {
        if (e is HttpException) {
            return e.code()
        }
        //Return general error code
        return Constants.HttpError.INTERNAL_SERVER_ERROR
    }
}