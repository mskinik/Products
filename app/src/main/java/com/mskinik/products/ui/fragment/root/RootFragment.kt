package com.mskinik.products.ui.fragment.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.mskinik.products.R
import com.mskinik.products.databinding.FragmentRootBinding
import com.mskinik.util.orZero
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RootViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.fragment_container) as? NavHostFragment
        val navController = navHostFragment?.navController
        navController?.let {
            binding.bottomNav.setupWithNavController(it)
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    val bottomNavigationView = binding.bottomNav
                    val badge = bottomNavigationView.getOrCreateBadge(R.id.basketFragment)
                    updateBadge(badge, it.totalQuantity.orZero())
                }
            }
        }
    }

    private fun updateBadge(badge: BadgeDrawable, count: Int) {
        if (count > 0) {
            badge.isVisible = true
            badge.number = count
        } else {
            badge.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}