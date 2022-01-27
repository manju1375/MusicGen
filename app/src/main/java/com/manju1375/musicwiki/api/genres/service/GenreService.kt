package com.manju1375.musicwiki.api.genres.service

import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.request.GenresInfoRequest
import com.manju1375.musicwiki.api.genres.request.GenresRequest
import io.reactivex.Single
import retrofit2.http.Body

interface GenreService {
    fun getGenres(@Body genresRequest: GenresRequest): Single<GenresTagDetails>
    fun getGenreInfo(@Body genresInfoRequest: GenresInfoRequest):Single<GenresTagDetails>
}