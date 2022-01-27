package com.manju1375.musicwiki.api

import com.manju1375.musicwiki.api.albums.service.AlbumService
import com.manju1375.musicwiki.api.albums.service.AlbumServiceImpl
import com.manju1375.musicwiki.api.artists.service.ArtistService
import com.manju1375.musicwiki.api.artists.service.ArtistServiceImpl
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.api.genres.service.GenreServiceImpl
import com.manju1375.musicwiki.api.tracks.service.TrackService
import com.manju1375.musicwiki.api.tracks.service.TrackServiceImpl
import retrofit2.Retrofit

class MusicWikiApiLoader(retrofit: Retrofit) : MusicWikiApi {

    override val albumService: AlbumService by lazy {
        AlbumServiceImpl(retrofit)
    }
    override val genreService: GenreService by lazy {
        GenreServiceImpl(retrofit)
    }
    override val trackService: TrackService by lazy {
        TrackServiceImpl(retrofit)
    }
    override val artistService: ArtistService by lazy {
        ArtistServiceImpl(retrofit)
    }
}
