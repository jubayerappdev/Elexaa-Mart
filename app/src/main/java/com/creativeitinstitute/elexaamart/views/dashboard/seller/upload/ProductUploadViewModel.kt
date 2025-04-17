package com.creativeitinstitute.elexaamart.views.dashboard.seller.upload

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.data.repositories.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductUploadViewModel @Inject constructor(private val repo: SellerRepository) :
    ViewModel() {

    private val _productUploadResponse = MutableLiveData<DataState<String>>()

    val productUploadResponse: LiveData<DataState<String>> = _productUploadResponse


    fun productUpload(product: Product) {
        _productUploadResponse.postValue(DataState.Loading())

        val imageUri: Uri = product.imageLink.toUri()

        repo.uploadProductImage(imageUri).addOnSuccessListener { snapshot ->

            snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->

                product.imageLink = url.toString()

                repo.uploadProduct(product).addOnSuccessListener {
                    _productUploadResponse.postValue(DataState.Success("Uploaded and Updated Product Successfully"))
                }.addOnFailureListener {
                    _productUploadResponse.postValue(DataState.Error("${it.message}"))
                }


            }


        }.addOnFailureListener {
            _productUploadResponse.postValue(DataState.Error("Image Uploaded fail"))
        }


    }

}
