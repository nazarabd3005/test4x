package com.nazar.test4x.security.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nazar.test4x.core.util.autoCleared
import com.nazar.test4x.security.databinding.FragmentInputCodeBinding
import com.poovam.pinedittextfield.PinField
import dagger.hilt.android.AndroidEntryPoint

class InputCodeFragment : Fragment() {

    private var binding: FragmentInputCodeBinding by autoCleared()

    private val viewmodel: InputCodeViewModel by viewModels()

    private val codeInput = "1234"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lineField.onTextCompleteListener = object : PinField.OnTextCompleteListener {
            override fun onTextComplete(enteredText: String): Boolean {
                if (enteredText == codeInput) {
                    Toast.makeText(requireContext(),"sukses login",Toast.LENGTH_LONG).show()
                    Handler().postDelayed({

                        val resultIntent = Intent()
                        resultIntent.putExtra("lockscreen", "finished")
                        requireActivity().setResult(Activity.RESULT_OK, resultIntent)
                        requireActivity().finish()
                    }, 1000)
                }
                return enteredText == codeInput
            }
        }
    }
}