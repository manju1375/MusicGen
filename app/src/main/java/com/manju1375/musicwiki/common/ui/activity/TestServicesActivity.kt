package com.manju1375.musicwiki.common.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.manju1375.musicwiki.albums.viewmodel.AlbumsViewModel
import com.manju1375.musicwiki.albums.viewmodel.AlbumsViewModelFactory
import com.manju1375.musicwiki.artists.viewmodel.ArtistsViewModel
import com.manju1375.musicwiki.databinding.ActivityMain2Binding
import com.manju1375.musicwiki.genres.viewmodel.ArtistsViewModelFactory
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModel
import com.manju1375.musicwiki.tracks.viewmodel.TracksViewModel
import com.manju1375.musicwiki.tracks.viewmodel.TracksViewModelFactory


class TestServicesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    private val genresViewModel: GenresViewModel by viewModels(
        factoryProducer = { com.manju1375.musicwiki.genres.viewmodel.ArtistsViewModelFactory() }
    )
    private val albumsViewModel: AlbumsViewModel by viewModels(
        factoryProducer = { AlbumsViewModelFactory() }
    )
    private val tracksViewModel: TracksViewModel by viewModels(
        factoryProducer = { TracksViewModelFactory() }
    )
    private val artistsViewModel: ArtistsViewModel by viewModels(
        factoryProducer = { ArtistsViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.genres.setOnClickListener { view ->
            genresViewModel.fetchGenres()
        }
        binding.genreInfo.setOnClickListener { view ->
            genresViewModel.fetchGenresInfo()
        }
        binding.albums.setOnClickListener { view ->
            albumsViewModel.fetchAlbumsForTag("disco")
        }
        binding.artists.setOnClickListener { view ->
            artistsViewModel.fetchArtists("disco")
        }
        binding.tracks.setOnClickListener { view ->
            tracksViewModel.fetchTracksForTag("disco")
        }
    }
}