package com.manju1375.musicwiki.albums.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.manju1375.musicwiki.albums.viewmodel.AlbumInfoDetailsViewModel
import com.manju1375.musicwiki.albums.viewmodel.AlbumInfoDetailsViewModelFactory
import com.manju1375.musicwiki.databinding.ActivityAlbumInfoLayoutBinding
import com.manju1375.musicwiki.genres.adapter.GenresAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity


class AlbumInfoActivity : AppCompatActivity(), OnOffsetChangedListener {

    private var _binding: ActivityAlbumInfoLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var mIsAvatarShown = true
    private var mAlbumImageView: AppCompatImageView? = null
    private var mMaxScrollSize = 0
    private var mDetailsLayout: LinearLayoutCompat? = null
    var selectedAlbumParams: List<String>? = null
    private val albumInfoDetailsViewModel: AlbumInfoDetailsViewModel by viewModels(
        factoryProducer = { AlbumInfoDetailsViewModelFactory() }
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAlbumInfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedAlbumParams = intent.getStringArrayExtra("selectedAlbum")?.asList()
        mAlbumImageView = binding.albumIv
        mDetailsLayout = binding.detailsLayout
        binding.lifecycleOwner = this
        binding.viewmodel = albumInfoDetailsViewModel
        val appbarLayout = binding.materialupAppbar
        appbarLayout.addOnOffsetChangedListener(this)
        binding.albumGenresList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.albumGenresList.adapter = GenresAdapter(object : GenresAdapter.OnGenreItemClickListener{
            override fun onItemClick(genreItem: String) {
                startActivity(Intent(this@AlbumInfoActivity, GenreDetailsActivity::class.java).apply{
                    putExtras(Bundle().apply {  putString("selectedGenre",genreItem)})
                })
            }
        })

        (binding.albumGenresList.adapter as GenresAdapter).expandRecyclerView(true)
        mMaxScrollSize = appbarLayout.totalScrollRange
        albumInfoDetailsViewModel.fetchAlbumInfo(selectedAlbumParams)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
       if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
        val percentage = Math.abs(i) * 100 / mMaxScrollSize
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mAlbumImageView!!.animate()
                .scaleY(0f).scaleX(0f)
                .setDuration(200)
                .start()
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mAlbumImageView!!.animate()
                .scaleY(1f).scaleX(1f)
                .start()
        }
    }
    companion object {
        private const val PERCENTAGE_TO_ANIMATE_AVATAR = 20
    }
}