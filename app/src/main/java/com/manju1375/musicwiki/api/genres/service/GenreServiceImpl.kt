package com.manju1375.musicwiki.api.genres.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.request.GenresInfoRequest
import com.manju1375.musicwiki.api.genres.request.GenresRequest
import io.reactivex.Single
import retrofit2.Retrofit

class GenreServiceImpl(retrofit: Retrofit):GenreService {
    override fun getGenres(genresRequest: GenresRequest): Single<GenresTagDetails> {
        TODO("Not yet implemented")
    }

    override fun getGenreInfo(genresInfoRequest: GenresInfoRequest): Single<GenresTagDetails> {
        TODO("Not yet implemented")
    }

}