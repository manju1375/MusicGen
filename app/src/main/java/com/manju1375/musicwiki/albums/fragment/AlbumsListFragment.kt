package com.manju1375.musicwiki.albums.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.albums.adapter.AlbumsAdapter
import com.manju1375.musicwiki.albums.viewmodel.AlbumsViewModel
import com.manju1375.musicwiki.albums.viewmodel.AlbumsViewModelFactory
import com.manju1375.musicwiki.common.ItemOffsetDecoration
import com.manju1375.musicwiki.databinding.FragmentAlbumsListBinding

class AlbumsListFragment (val selectedGenre:String?): Fragment() {

    private var _binding: FragmentAlbumsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val albumsViewModel: AlbumsViewModel by viewModels(
        factoryProducer = { AlbumsViewModelFactory() }
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = albumsViewModel
        context?.let { ItemOffsetDecoration(it, R.dimen.dp_10) }?.let { binding.albumsList.addItemDecoration(it) }
        binding.albumsList.adapter = AlbumsAdapter()
        albumsViewModel.setLoaderVisibility(View.VISIBLE)
        albumsViewModel.fetchAlbumsForTag(selectedGenre)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        albumsViewModel.let {
            listOf(
                it.albumsViewState,
                it.loaderVisibility
            ).forEach { liveData ->
                liveData.removeObservers(viewLifecycleOwner)
            }
        }
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedGenre: String?): AlbumsListFragment {
            val fragment = AlbumsListFragment(selectedGenre)
            return fragment
        }
    }
}