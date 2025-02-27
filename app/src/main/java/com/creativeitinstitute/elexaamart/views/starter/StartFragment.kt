package com.creativeitinstitute.elexaamart.views.starter

import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.databinding.FragmentStartBinding


class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {





    override fun setListener() {

        with(binding){

            btnLogin.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_loginFragment)

            }
            btnRegister.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_registerFragment)

            }
        }

    }

    override fun allObserver() {

    }


}