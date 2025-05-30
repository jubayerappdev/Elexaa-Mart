package com.creativeitinstitute.elexaamart.views.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.models.UserLogin

import com.creativeitinstitute.elexaamart.data.repositories.AuthRepository
import com.creativeitinstitute.elexaamart.views.dashboard.seller.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthRepository) : ViewModel() {

   private val _loginResponse = MutableLiveData<DataState<Profile>>()

    val loginResponse:LiveData<DataState<Profile>> = _loginResponse


    fun userLogin(user: UserLogin){
        _loginResponse.postValue(DataState.Loading())



        authService.userLogin(user)
            .addOnSuccessListener {
//                _loginResponse.postValue(DataState.Success(user))

         checkUserByID(it.user?.uid)


        }.addOnFailureListener {error->
                _loginResponse.postValue(DataState.Error("${error.message}"))

            }

    }

     fun checkUserByID(uid: String?) {

         uid?.let { userID ->
             authService.getUserByUserID(userID).addOnSuccessListener { value->

                 _loginResponse.postValue(DataState.Success(
                     value.documents[0].toObject(
                         Profile::class.java
                     )
                 ))

             }.addOnFailureListener { error->
                 _loginResponse.postValue(DataState.Error("${error.message}"))
             }

         }


    }
}
