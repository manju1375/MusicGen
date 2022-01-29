package com.manju1375.musicwiki.genres.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.databinding.FragmentGenresListBinding
import com.manju1375.musicwiki.genres.GenresAdapter
import com.manju1375.musicwiki.genres.ItemOffsetDecoration
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModel
import com.manju1375.musicwiki.genres.viewmodel.GenresViewModelFactory

class GenresListFragment : Fragment() {

    private var _binding: FragmentGenresListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val genresViewModel: GenresViewModel by viewModels(
        factoryProducer = { GenresViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenresListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = genresViewModel
        context?.let { ItemOffsetDecoration(it, R.dimen.dp_10) }?.let { binding.bagItemsList.addItemDecoration(it) }
        binding.bagItemsList.adapter = GenresAdapter()
        genresViewModel.setLoaderVisibility(View.VISIBLE)
        genresViewModel.fetchGenres()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        genresViewModel.let {
            listOf(
                it.genresViewState,
                it.loaderVisibility
            ).forEach { liveData ->
                liveData.removeObservers(viewLifecycleOwner)
            }
        }
        _binding = null
    }
}