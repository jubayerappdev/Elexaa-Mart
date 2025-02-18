package com.creativeitinstitute.elexaamart.views.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container,false)

        setListener()
        return binding.root
    }

    private fun setListener() {

        with(binding){

            btnLogin.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_loginFragment)

            }
            btnRegister.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_registerFragment)

            }
        }

    }


}