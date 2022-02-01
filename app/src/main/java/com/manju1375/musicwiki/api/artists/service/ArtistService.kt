package com.manju1375.musicwiki.api.artists.service

import com.manju1375.musicwiki.api.albums.model.Album
import com.manju1375.musicwiki.api.albums.model.AlbumDetails
import com.manju1375.musicwiki.api.albums.model.Albums
import com.manju1375.musicwiki.api.artists.model.ArtistInfo
import com.manju1375.musicwiki.api.artists.model.ArtistsDetails
import com.manju1375.musicwiki.api.artists.model.TopAlbumDetails
import com.manju1375.musicwiki.api.artists.model.TopTracksDetails
import com.manju1375.musicwiki.api.tracks.model.Tracks
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ArtistService {
    @GET()
    fun getArtists(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<ArtistsDetails>
    @GET()
    fun getArtistInfo(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<ArtistInfo>
    @GET()
    fun getTopTracksForArtist(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<TopTracksDetails>
    @GET()
    fun getTopAlbumsForArtist(@Url url:String, @QueryMap hashMap:HashMap<String, String>): Single<TopAlbumDetails>
    fun getErrorCode(e: Throwable?): Int
}