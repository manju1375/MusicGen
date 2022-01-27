package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.request.ArtistsRequest
import io.reactivex.Single
import retrofit2.http.Body

interface ArtistService {
    fun getArtists(@Body artistsRequest: ArtistsRequest): Single<ArtistsDetails>
}