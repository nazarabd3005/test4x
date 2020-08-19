package com.nazar.test4x.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nazar.test4x.core.util.autoCleared
import com.nazar.test4x.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var binding : FragmentProfileBinding by autoCleared()

    private val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = profileViewModel
        }
        return binding.root
    }
}