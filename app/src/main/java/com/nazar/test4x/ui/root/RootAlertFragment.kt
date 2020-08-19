package com.nazar.test4x.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nazar.test4x.R
import com.nazar.test4x.databinding.FragmentRootBinding

class RootAlertFragment : Fragment() {

    private lateinit var binding: FragmentRootBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRootBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonClose.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }
}