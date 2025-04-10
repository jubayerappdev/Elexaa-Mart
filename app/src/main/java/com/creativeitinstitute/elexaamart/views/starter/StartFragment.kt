package com.creativeitinstitute.elexaamart.views.starter

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.databinding.FragmentStartBinding
import com.creativeitinstitute.elexaamart.views.dashboard.seller.SellerDashboard
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

            startActivity(Intent(requireContext(), SellerDashboard::class.java))
            requireActivity().finish()
        }

    }

    override fun allObserver() {

    }


}