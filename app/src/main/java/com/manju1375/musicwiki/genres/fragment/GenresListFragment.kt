package com.manju1375.musicwiki.genres.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.common.ItemOffsetDecoration
import com.manju1375.musicwiki.databinding.FragmentGenresListBinding
import com.manju1375.musicwiki.genres.activity.GenreDetailsActivity
import com.manju1375.musicwiki.genres.adapter.GenresAdapter
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
    ): View {
        _binding = FragmentGenresListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = genresViewModel
        context?.let { ItemOffsetDecoration(it, R.dimen.dp_10) }?.let { binding.genresList.addItemDecoration(it) }
        binding.genresList.adapter = GenresAdapter(object : GenresAdapter.OnGenreItemClickListener {
            override fun onItemClick(genreItem: String) {
                startActivity(Intent(activity, GenreDetailsActivity::class.java).apply {
                    putExtras(Bundle().apply { putString("selectedGenre", genreItem) })
                })
            }
        })

        (binding.genresList.adapter as GenresAdapter).expandRecyclerView(false)
        genresViewModel.setLoaderVisibility(View.VISIBLE)
        genresViewModel.fetchGenres()
        binding.buttonExpand.setOnClickListener {
            (binding.genresList.adapter as GenresAdapter).isExpandable?.let { isExpandable ->
                if (!isExpandable) {
                    (binding.genresList.adapter as GenresAdapter).expandRecyclerView(true)
                    binding.buttonExpand.text = context?.resources?.getText(R.string.collapse)
                } else {
                    (binding.genresList.adapter as GenresAdapter).expandRecyclerView(false)
                    binding.buttonExpand.text = context?.resources?.getText(R.string.expand)
                }
            }
        }
        return binding.root
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