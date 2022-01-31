package com.manju1375.musicwiki.albums.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.common.ItemOffsetDecoration
import com.manju1375.musicwiki.databinding.FragmentTracksListBinding
import com.manju1375.musicwiki.tracks.adapter.TracksAdapter
import com.manju1375.musicwiki.tracks.viewmodel.TracksViewModel
import com.manju1375.musicwiki.tracks.viewmodel.TracksViewModelFactory

class TracksListFragment(val selectedGenre:String?) : Fragment() {

    private var _binding: FragmentTracksListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val tracksViewModel: TracksViewModel by viewModels(
        factoryProducer = { TracksViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = tracksViewModel
        context?.let { ItemOffsetDecoration(it, R.dimen.dp_10) }?.let { binding.tracksList.addItemDecoration(it) }
        binding.tracksList.adapter = TracksAdapter()
        tracksViewModel.setLoaderVisibility(View.VISIBLE)
        tracksViewModel.fetchTracksForTag(selectedGenre)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tracksViewModel.let {
            listOf(
                it.tracksViewState,
                it.loaderVisibility
            ).forEach { liveData ->
                liveData.removeObservers(viewLifecycleOwner)
            }
        }
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedGenre: String?): TracksListFragment {
            val fragment = TracksListFragment(selectedGenre)
            return fragment
        }
    }
}