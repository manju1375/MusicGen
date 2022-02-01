package com.manju1375.musicwiki.albums.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.manju1375.musicwiki.albums.viewmodel.AlbumInfoDetailsViewModel
import com.manju1375.musicwiki.albums.viewmodel.AlbumInfoDetailsViewModelFactory
import com.manju1375.musicwiki.databinding.ActivityAlbumInfoLayoutBinding
import com.manju1375.musicwiki.genres.adapter.GenresAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.common.ItemOffsetDecoration
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity


class AlbumInfoActivity : AppCompatActivity() {

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
        binding.albumGenresList.addItemDecoration(ItemOffsetDecoration(this, R.dimen.dp_10))
        binding.albumGenresList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.albumGenresList.adapter = GenresAdapter(object : GenresAdapter.OnGenreItemClickListener {
            override fun onItemClick(genreItem: String) {
                startActivity(Intent(this@AlbumInfoActivity, GenreDetailsActivity::class.java).apply {
                    putExtras(Bundle().apply { putString("selectedGenre", genreItem) })
                })
            }
        })

        (binding.albumGenresList.adapter as GenresAdapter).expandRecyclerView(true)
        albumInfoDetailsViewModel.fetchAlbumInfo(selectedAlbumParams)
        albumInfoDetailsViewModel.infoViewState.observe(this, Observer {
            it.let {
                binding.albumIv.load(it?.image?.get(4)?.text) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.black_bg)
                }
            }
        })
    }

    companion object {
        private const val PERCENTAGE_TO_ANIMATE_AVATAR = 20
    }

    override fun onDestroy() {
        super.onDestroy()
        albumInfoDetailsViewModel.let {
            listOf(
                it.infoViewState,
                it.loaderVisibility
            ).forEach { liveData ->
                liveData.removeObservers(this)
            }
        }
        _binding = null
    }
}