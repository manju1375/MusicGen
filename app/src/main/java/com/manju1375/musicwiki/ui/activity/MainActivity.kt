package com.manju1375.musicwiki.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.albums.AlbumsViewModel
import com.manju1375.musicwiki.albums.AlbumsViewModelFactory
import com.manju1375.musicwiki.artists.ArtistsViewModel
import com.manju1375.musicwiki.artists.ArtistsViewModelFactory
import com.manju1375.musicwiki.databinding.ActivityMain2Binding
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModel
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModelFactory
import com.manju1375.musicwiki.tracks.TracksViewModel
import com.manju1375.musicwiki.tracks.TracksViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    private val genresViewModel: GenresViewModel by viewModels(
        factoryProducer = { GenresViewModelFactory() }
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
            albumsViewModel.fetchAlbumsForTag()
        }
        binding.artists.setOnClickListener { view ->
            artistsViewModel.fetchArtistsForTag()
        }
        binding.tracks.setOnClickListener { view ->
            tracksViewModel.fetchTracksForTag()
        }
    }
}