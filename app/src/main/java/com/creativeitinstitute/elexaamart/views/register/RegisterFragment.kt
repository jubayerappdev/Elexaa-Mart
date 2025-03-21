package com.creativeitinstitute.elexaamart.views.register

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.models.UserRegister
import com.creativeitinstitute.elexaamart.databinding.FragmentRegisterBinding
import com.creativeitinstitute.elexaamart.isEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val viewModel : RegistrationViewModel by viewModels ()


    override  fun setListener() {

        with(binding){

            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()

                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()){

//                    Toast.makeText(context, "All input done !", Toast.LENGTH_LONG).show()
                    val user = UserRegister(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        userType = "Seller",
                        userID = ""
                    )

                    viewModel.userRegistration(user)
                }


            }
        }
    }

    override fun allObserver() {
        registrationObserver()
    }
//OOAD-> Object Oriented Analysis Design (Code Design)
    fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "created User: ${it.data}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_dashFragment)
                }
            }
        }



    }


}