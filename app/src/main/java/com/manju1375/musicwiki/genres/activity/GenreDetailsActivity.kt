/*
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manju1375.musicwiki.genres.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayout
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.albums.fragment.AlbumsListFragment
import com.manju1375.musicwiki.albums.fragment.ArtistsListFragment
import com.manju1375.musicwiki.albums.fragment.TracksListFragment
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity

class GenreDetailsActivity : AppCompatActivity(), OnOffsetChangedListener {
    private var mIsAvatarShown = true
    private var detailsLayout: LinearLayoutCompat? = null
    private var mMaxScrollSize = 0
    var selectedGenre: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genre_details_layout)
        selectedGenre = intent.getStringExtra("selectedGenre")
        val tabLayout = findViewById<View>(R.id.materialup_tabs) as TabLayout
        val viewPager = findViewById<View>(R.id.materialup_viewpager) as ViewPager
        val appbarLayout = findViewById<View>(R.id.materialup_appbar) as AppBarLayout
        detailsLayout = findViewById<LinearLayoutCompat>(R.id.detailslayout) as LinearLayoutCompat
        val toolbar = findViewById<View>(R.id.materialup_toolbar) as Toolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }
        appbarLayout.addOnOffsetChangedListener(this)
        mMaxScrollSize = appbarLayout.totalScrollRange
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