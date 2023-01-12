package com.masungging.infocuaca.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.masungging.infocuaca.R
import com.masungging.infocuaca.databinding.FragmentCuacaListBinding

// Kelas fragmen untuk menunjukkan status layanan API.
class CuacaListFragment : Fragment() {

    private val viewModel: OverViewModel by activityViewModels()

    // Mengaktifkan Data Binding dan menyetel tata letak CuacaListFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = FragmentCuacaListBinding.inflate(inflater)
        viewModel.getCuacaTerkiniList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = CuacaListAdapter(CuacaListener { detail ->
            viewModel.onCuacaClicked(detail)
            findNavController()
                .navigate(R.id.action_cuacaListFragment_to_cuacaDetailFragment)
        })

        // Menetapkan tata letak dengan root
        return binding.root
    }
}