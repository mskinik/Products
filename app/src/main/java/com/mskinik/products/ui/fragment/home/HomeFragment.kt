package com.mskinik.products.ui.fragment.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mskinik.products.R
import com.mskinik.products.databinding.FragmentHomeBinding
import com.mskinik.products.ui.adapter.HomeProductAdapter
import com.mskinik.products.ui.fragment.root.RootEvent
import com.mskinik.products.ui.fragment.root.RootViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val rootViewModel by activityViewModels<RootViewModel>()

    @Inject
    lateinit var homeProductAdapter: HomeProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = homeProductAdapter
        homeProductAdapter.setClickListener { id ->
            rootViewModel.setEvent(RootEvent.NavigateToProductDetail(id))
        }
        etStock.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setEvent(
                    HomeViewEvent.OnStockFilterChanged(
                        s.toString().toIntOrNull() ?: 0
                    )
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        etWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setEvent(
                    HomeViewEvent.OnWeightFilterChanged(
                        s.toString().toIntOrNull() ?: 0
                    )
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setEvent(HomeViewEvent.OnSearchTextChanged(s.toString()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        btnSort.setOnClickListener {
            viewModel.setEvent(HomeViewEvent.OnSortClicked)
        }
        collectState()
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    homeProductAdapter.submitList(it.filteredProductList)
                    if (it.searchText != binding.etSearch.text.toString()) {
                        binding.etSearch.setText(it.searchText)
                        it.searchText?.let { text ->
                            binding.etSearch.setSelection(text.length)
                        }
                    }
                    if (it.minStock != binding.etStock.text.toString().toIntOrNull()) {
                        binding.etStock.setText(it.minStock.toString())
                        it.minStock?.let { text ->
                            binding.etStock.setSelection(text.toString().length)
                        }
                    }
                    if (it.minWeight != binding.etWeight.text.toString().toIntOrNull()) {
                        binding.etWeight.setText(it.minWeight.toString())
                        it.minWeight?.let { text ->
                            binding.etWeight.setSelection(text.toString().length)
                        }
                    }
                    if (it.sorting == Sorting.ASCENDING) {
                        binding.btnSort.text = getString(R.string.asc)
                        binding.btnSort.setTextColor(resources.getColor(R.color.light_green, null))
                    } else if (it.sorting == Sorting.DESCENDING) {
                        binding.btnSort.text = getString(R.string.dsc)
                        binding.btnSort.setTextColor(resources.getColor(R.color.light_red, null))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}