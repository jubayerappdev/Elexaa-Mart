package com.creativeitinstitute.elexaamart.views.starter

import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {





    override fun setListener() {

        setUpAutoLogin()

        with(binding){

            btnLogin.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_loginFragment)

            }
            btnRegister.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_registerFragment)

            }
        }

    }

    private fun setUpAutoLogin() {
        FirebaseAuth.getInstance().currentUser?.let {

            findNavController().navigate(R.id.action_startFragment_to_dashFragment)
        }

    }

    override fun allObserver() {

    }


}