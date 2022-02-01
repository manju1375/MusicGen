package com.manju1375.musicwiki.artists.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.manju1375.musicwiki.genres.adapter.GenresAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.manju1375.musicwiki.albums.adapter.AlbumsAdapter
import com.manju1375.musicwiki.artists.viewmodel.ArtistInfoDetailsViewModel
import com.manju1375.musicwiki.artists.viewmodel.ArtistInfoDetailsViewModelFactory
import com.manju1375.musicwiki.databinding.ActivityArtistInfoLayoutBinding
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity
import com.manju1375.musicwiki.tracks.adapter.TracksAdapter


class ArtistInfoActivity : AppCompatActivity(), OnOffsetChangedListener {

    private var _binding: ActivityArtistInfoLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var mIsAvatarShown = true
    private var mArtistImageView: AppCompatImageView? = null
    private var mMaxScrollSize = 0
    private var mDetailsLayout: LinearLayoutCompat? = null
    var selectedArtist: String? = null
    private val artistInfoDetailsViewModel: ArtistInfoDetailsViewModel by viewModels(
        factoryProducer = { ArtistInfoDetailsViewModelFactory() }
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArtistInfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedArtist = intent.getStringExtra("selectedArtist")
        mArtistImageView = binding.artistIv
        mDetailsLayout = binding.detailsLayout
        binding.lifecycleOwner = this
        binding.viewmodel = artistInfoDetailsViewModel
        val appbarLayout = binding.materialupAppbar
        appbarLayout.addOnOffsetChangedListener(this)
        binding.artistGenresList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.artistGenresList.adapter = GenresAdapter(object : GenresAdapter.OnGenreItemClickListener{
            override fun onItemClick(genreItem: String) {
                startActivity(Intent(this@ArtistInfoActivity, GenreDetailsActivity::class.java).apply{
                    putExtras(Bundle().apply {  putString("selectedGenre",genreItem)})
                })
            }
        })
        binding.topTracksList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.topAlbumsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.topTracksList.adapter = TracksAdapter()
        binding.topAlbumsList.adapter = AlbumsAdapter()
        (binding.artistGenresList.adapter as GenresAdapter).expandRecyclerView(true)
        mMaxScrollSize = appbarLayout.totalScrollRange
        artistInfoDetailsViewModel.fetchArtistInfo(selectedArtist)
        artistInfoDetailsViewModel.fetchTopTracksForArtist(selectedArtist)
        artistInfoDetailsViewModel.fetchTopAlbumsForArtist(selectedArtist)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
       if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
        val percentage = Math.abs(i) * 100 / mMaxScrollSize
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mArtistImageView!!.animate()
                .scaleY(0f).scaleX(0f)
                .setDuration(200)
                .start()
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mArtistImageView!!.animate()
                .scaleY(1f).scaleX(1f)
                .start()
        }
    }
    companion object {
        private const val PERCENTAGE_TO_ANIMATE_AVATAR = 20
    }
}