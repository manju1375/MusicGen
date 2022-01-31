package com.manju1375.musicwiki.genres.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayout
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.albums.fragment.AlbumsListFragment
import com.manju1375.musicwiki.albums.fragment.ArtistsListFragment
import com.manju1375.musicwiki.albums.fragment.TracksListFragment
import com.manju1375.musicwiki.databinding.ActivityGenreDetailsLayoutBinding
import com.manju1375.musicwiki.databinding.ActivityMain2Binding
import com.manju1375.musicwiki.databinding.FragmentGenresListBinding
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity
import com.manju1375.musicwiki.genres.viewmodel.GenresDetailsViewModel
import com.manju1375.musicwiki.genres.viewmodel.GenresDetailsViewModelFactory
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModel
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModelFactory

class GenreDetailsActivity : AppCompatActivity(), OnOffsetChangedListener {

    private var _binding: ActivityGenreDetailsLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private var mIsAvatarShown = true
    private var detailsLayout: LinearLayoutCompat? = null
    private var mMaxScrollSize = 0
    var selectedGenre: String? = null
    private val genresDetailsViewModel: GenresDetailsViewModel by viewModels(
        factoryProducer = { GenresDetailsViewModelFactory() }
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGenreDetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedGenre = intent.getStringExtra("selectedGenre")
        binding.lifecycleOwner = this
        binding.viewmodel = genresDetailsViewModel
        val tabLayout = binding.materialupTabs
        val viewPager = binding.materialupViewpager
        val appbarLayout = binding.materialupAppbar
        detailsLayout = binding.detailslayout
        val toolbar = binding.materialupToolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }
        appbarLayout.addOnOffsetChangedListener(this)
        mMaxScrollSize = appbarLayout.totalScrollRange
        genresDetailsViewModel.fetchGenresInfo(selectedGenre)
        viewPager.adapter = selectedGenre?.let { TabsAdapter(supportFragmentManager, it) }
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
       if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
        val percentage = Math.abs(i) * 100 / mMaxScrollSize
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false
            detailsLayout!!.animate()
                .scaleY(0f).scaleX(0f)
                .setDuration(200)
                .start()
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true
            detailsLayout!!.animate()
                .scaleY(1f).scaleX(1f)
                .start()
        }
    }

    private class TabsAdapter internal constructor(fm: FragmentManager?,selectedGenre: String) : FragmentPagerAdapter(fm!!) {
        val selectedGenreParam = selectedGenre
        override fun getCount(): Int {
            return TAB_COUNT
        }

        override fun getItem(position: Int):Fragment {
            return when(position){
                0-> AlbumsListFragment.newInstance(selectedGenreParam)
                1-> ArtistsListFragment.newInstance(selectedGenreParam)
                else -> TracksListFragment.newInstance(selectedGenreParam)
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when(position){
                0-> "Albums"
                1-> "Artists"
                else -> "Tracks"
            }
        }

        companion object {
            private const val TAB_COUNT = 3
        }
    }

    companion object {
        private const val PERCENTAGE_TO_ANIMATE_AVATAR = 20
        fun start(c: Context) {
            c.startActivity(Intent(c, GenreDetailsActivity::class.java))
        }
    }
}