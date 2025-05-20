package com.creativeitinstitute.elexaamart.views.dashboard.seller.profile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.data.repositories.SellerProfileRepository
import com.creativeitinstitute.elexaamart.data.repositories.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerProfileViewModel @Inject constructor(private val repo: SellerProfileRepository) :
    ViewModel() {

    private val _profileUpdateResponse = MutableLiveData<DataState<String>>()

    val profileUpdateResponse: LiveData<DataState<String>> = _profileUpdateResponse


    fun updateProfile(user: Profile, hasLocalImageUrl: Boolean) {
        _profileUpdateResponse.postValue(DataState.Loading())

        if (hasLocalImageUrl){
            val imageUri: Uri? = user.userImage?.toUri()

            imageUri?.let {
                repo.uploadImage(it).addOnSuccessListener { snapshot ->

                    snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->

                        user.userImage = url.toString()

                        repo.updateUser(user).addOnSuccessListener {
                            _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated Product Successfully"))
                        }.addOnFailureListener {
                            _profileUpdateResponse.postValue(DataState.Error("${it.message}"))
                        }


                    }


                }.addOnFailureListener {
                    _profileUpdateResponse.postValue(DataState.Error("Image Uploaded fail"))
                }
            }
        }else{
            repo.updateUser(user).addOnSuccessListener {
                _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated Product Successfully"))
            }.addOnFailureListener {
                _profileUpdateResponse.postValue(DataState.Error("${it.message}"))
            }
        }






    }

    private val _logedInUserResponse = MutableLiveData<DataState<Profile>>()

    val logedInUserResponse: LiveData<DataState<Profile>> get()  = _logedInUserResponse

    fun getUserByUserID(userID: String){
        _logedInUserResponse.postValue(DataState.Loading())

        repo.getUserByUserID(userID).addOnSuccessListener { value->

            _logedInUserResponse.postValue(DataState.Success(
                value.documents[0].toObject(
                    Profile::class.java
                )
            ))

        }

    }

}
