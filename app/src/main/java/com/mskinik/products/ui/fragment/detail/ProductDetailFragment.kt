package com.mskinik.products.ui.fragment.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mskinik.products.R
import com.mskinik.products.databinding.FragmentProductDetailBinding
import com.mskinik.products.ui.compose.applyComposeView
import com.mskinik.products.ui.compose.detail.ProductDetailComposeView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by viewModels<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
        collectEffect()

    }

    private fun collectState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    binding.composeView.applyComposeView {
                        ProductDetailComposeView(
                            it,
                            onFavoriteClick = { viewModel.setEvent(ProductDetailEvent.OnFavoriteClick )},
                            onAddToCartClick = {viewModel.setEvent(ProductDetailEvent.AddToCart) },
                            onDecreaseClick = {viewModel.setEvent(ProductDetailEvent.DecreaseQuantity) },
                            onIncreaseClick = { viewModel.setEvent(ProductDetailEvent.IncreaseQuantity) },
                            onBackClick = { viewModel.setEvent(ProductDetailEvent.OnBackClicked) }
                        )
                    }
                }
            }
        }
    }

    private fun collectEffect() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.effect.collect {
                    when (it) {
                        is ProductDetailEffect.Back -> {
                            findNavController().popBackStack()
                        }
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