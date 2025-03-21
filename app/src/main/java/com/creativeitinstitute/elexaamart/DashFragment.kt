package com.creativeitinstitute.elexaamart

import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.databinding.FragmentDashBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashFragment : BaseFragment<FragmentDashBinding>(FragmentDashBinding::inflate) {

    @Inject
    lateinit var jAuth: FirebaseAuth
    override fun setListener() {

        with (binding){
            btnLogout.setOnClickListener {
                jAuth.signOut()
                findNavController().navigate(R.id.action_dashFragment_to_startFragment)

            }
        }

    }

    override fun allObserver() {

    }


}