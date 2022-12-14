package com.example.shoestoreapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestoreapp.R
import com.example.shoestoreapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        binding.btnNext.setOnClickListener {
                it.findNavController()
                    .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        return binding.root
    }

}