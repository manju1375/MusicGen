package com.manju1375.musicwiki.api

import com.manju1375.musicwiki.api.albums.service.AlbumService
import com.manju1375.musicwiki.api.artists.service.ArtistService
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.api.tracks.service.TrackService

interface MusicWikiApi {
    val genreService: GenreService
    val albumService : AlbumService
    val artistService: ArtistService
    val trackService: TrackService
}