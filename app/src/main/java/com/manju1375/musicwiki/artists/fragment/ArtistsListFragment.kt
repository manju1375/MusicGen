package com.manju1375.musicwiki.albums.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.artists.adapter.ArtistsAdapter
import com.manju1375.musicwiki.artists.viewmodel.ArtistsViewModel
import com.manju1375.musicwiki.common.ItemOffsetDecoration
import com.manju1375.musicwiki.databinding.FragmentArtistsListBinding
import com.manju1375.musicwiki.genres.viewmodel.ArtistsViewModelFactory

class ArtistsListFragment(val selectedGenre:String?) : Fragment() {

    private var _binding: FragmentArtistsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val artistsViewModel: ArtistsViewModel by viewModels(
        factoryProducer = { ArtistsViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = artistsViewModel
        context?.let { ItemOffsetDecoration(it, R.dimen.dp_10) }?.let { binding.artistList.addItemDecoration(it) }

        binding.artistList.adapter = ArtistsAdapter()
        artistsViewModel.setLoaderVisibility(View.VISIBLE)
        artistsViewModel.fetchArtists(selectedGenre)
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        artistsViewModel.let {
            listOf(
                it.artistsViewState,
                it.loaderVisibility
            ).forEach { liveData ->
                liveData.removeObservers(viewLifecycleOwner)
            }
        }
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedGenre: String?): ArtistsListFragment {
            val fragment = ArtistsListFragment(selectedGenre)
            return fragment
        }
    }
}