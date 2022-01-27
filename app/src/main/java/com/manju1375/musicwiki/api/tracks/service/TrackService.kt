package com.manju1375.musicwiki.api.tracks.service

import com.manju1375.musicwiki.api.tracks.model.TracksDetails
import io.reactivex.Single

interface TrackService {
    fun getTracks(genreTag:String): Single<TracksDetails>
}