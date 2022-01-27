package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.request.ArtistsRequest
import io.reactivex.Single
import retrofit2.Retrofit

class ArtistServiceImpl(retrofit: Retrofit):ArtistService {
    override fun getArtists(artistsRequest: ArtistsRequest): Single<ArtistsDetails> {
        TODO("Not yet implemented")
    }
}