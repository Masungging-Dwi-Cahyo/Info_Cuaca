package com.masungging.infocuaca.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.masungging.infocuaca.databinding.FragmentCuacaDetailBinding

// Kelas fragmen untuk menampilkan informasi detail
class CuacaDetailFragment : Fragment() {

    private val viewModel: OverViewModel by activityViewModels()

    // Mengaktifkan Data Binding dan menyetel tata letak untuk DetailListFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCuacaDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Menetapkan tata letak dengan root
        return binding.root
    }
}