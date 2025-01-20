package com.mskinik.products.ui.fragment.basket

import BasketComposeView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mskinik.products.databinding.FragmentBasketBinding
import com.mskinik.products.ui.compose.applyComposeView
import com.mskinik.products.ui.fragment.root.RootEvent
import com.mskinik.products.ui.fragment.root.RootViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BasketViewModel>()
    private val rootViewModel by activityViewModels<RootViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
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
                        BasketComposeView(
                            it,
                            onIncreaseClick = {
                                viewModel.setEvent(BasketEvent.IncreaseQuantity(it))
                            },
                            onDelete = {
                                viewModel.setEvent(BasketEvent.DeleteProductDetail(it))
                            },
                            onDecreaseClick = {
                                viewModel.setEvent(BasketEvent.DecreaseQuantity(it))
                            },
                            onCheckoutClick = {
                                viewModel.setEvent(BasketEvent.NavigateToCheckout)
                            }
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
                        is BasketEffect.NavigateToCheckout -> {
                            rootViewModel.setEvent(RootEvent.NavigateToCheckout)
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
