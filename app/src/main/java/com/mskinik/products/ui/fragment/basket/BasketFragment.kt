package com.mskinik.products.ui.fragment.basket

import BasketComposeView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mskinik.products.databinding.FragmentBasketBinding
import com.mskinik.products.ui.compose.applyComposeView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BasketViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
