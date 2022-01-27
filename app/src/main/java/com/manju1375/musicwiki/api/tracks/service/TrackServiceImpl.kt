package com.manju1375.musicwiki.api.tracks.service

import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import io.reactivex.Single
import retrofit2.Retrofit

class TrackServiceImpl(retrofit: Retrofit):TrackService {
    override fun getTracks(genreTag: String): Single<TracksDetails> {
        TODO("Not yet implemented")
    }

}