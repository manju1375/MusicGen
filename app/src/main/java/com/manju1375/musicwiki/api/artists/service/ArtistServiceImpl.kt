package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.albums.model.Albums
import com.manju1375.musicwiki.api.artists.model.ArtistInfo
import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.model.TopAlbumDetails
import com.manju1375.musicwiki.api.artists.model.TopTracksDetails
import com.manju1375.musicwiki.api.tracks.model.Tracks
import com.manju1375.musicwiki.config.Constants
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit

class ArtistServiceImpl(retrofit: Retrofit):ArtistService {
    private val artistService = retrofit.create(ArtistService::class.java)

    override fun getArtists(url: String, hashMap: HashMap<String, String>): Single<ArtistsDetails> {
        return artistService.getArtists(url,hashMap)
    }

    override fun getArtistInfo(url: String, hashMap: HashMap<String, String>): Single<ArtistInfo> {
        return  artistService.getArtistInfo(url,hashMap)
    }

    override fun getTopTracksForArtist(url: String, hashMap: HashMap<String, String>): Single<TopTracksDetails> {
        return artistService.getTopTracksForArtist(url,hashMap)
    }

    override fun getTopAlbumsForArtist(url: String, hashMap: HashMap<String, String>): Single<TopAlbumDetails   > {
        return artistService.getTopAlbumsForArtist(url,hashMap)
    }

    override fun getErrorCode(e: Throwable?): Int {
        if (e is HttpException) {
            return e.code()
        }
        //Return general error code
        return Constants.HttpError.INTERNAL_SERVER_ERROR
    }
}