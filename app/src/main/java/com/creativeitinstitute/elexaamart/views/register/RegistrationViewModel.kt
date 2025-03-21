package com.creativeitinstitute.elexaamart.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.models.UserRegister
import com.creativeitinstitute.elexaamart.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authService: AuthRepository) :
    ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<UserRegister>>()

    val registrationResponse: LiveData<DataState<UserRegister>> = _registrationResponse


    fun userRegistration(user: UserRegister) {
        _registrationResponse.postValue(DataState.Loading())



        authService.userRegistration(user)
            .addOnSuccessListener {
                it.user?.let { createdUser->

                    user.userID = createdUser.uid

                    authService.createUser(user).addOnSuccessListener {

                        _registrationResponse.postValue(DataState.Success(user))
                    }.addOnFailureListener { error ->
                        _registrationResponse.postValue(DataState.Error("${error.message}"))

                    }
                }



            }.addOnFailureListener { error ->
                _registrationResponse.postValue(DataState.Error("${error.message}"))

            }

    }
}
