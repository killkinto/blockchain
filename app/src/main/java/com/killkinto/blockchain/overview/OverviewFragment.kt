package com.killkinto.blockchain.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.killkinto.blockchain.BlockchainApplication
import com.killkinto.blockchain.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private val viewModel by viewModels<OverviewViewModel> {
        OverViewViewModelFactory(
            (requireContext().applicationContext as BlockchainApplication).blockchainRepository,
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = OverviewFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            quoteList.adapter = QuoteAdapter()
        }
        return binding.root
    }
}