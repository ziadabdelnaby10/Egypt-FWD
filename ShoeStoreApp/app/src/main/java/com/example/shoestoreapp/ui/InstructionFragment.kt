package com.example.shoestoreapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestoreapp.R
import com.example.shoestoreapp.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentInstructionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        binding.btnGetStarted.setOnClickListener {
            it.findNavController()
                .navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
        }
        return binding.root
    }


}