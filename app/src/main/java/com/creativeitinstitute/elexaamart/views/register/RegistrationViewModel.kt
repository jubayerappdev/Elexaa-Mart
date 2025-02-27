package com.creativeitinstitute.elexaamart.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.models.UserRegister
import com.creativeitinstitute.elexaamart.data.repositories.AuthRepository

class RegistrationViewModel : ViewModel() {

   private val _registrationResponse = MutableLiveData<DataState<UserRegister>>()

    val registrationResponse:LiveData<DataState<UserRegister>> = _registrationResponse


    fun userRegistration(user:UserRegister){
        _registrationResponse.postValue(DataState.Loading())

        val authService = AuthRepository()

        authService.userRegistration(user)
            .addOnSuccessListener {
                _registrationResponse.postValue(DataState.Success(user))


        }.addOnFailureListener {error->
                _registrationResponse.postValue(DataState.Error("${error.message}"))

            }

    }
}
