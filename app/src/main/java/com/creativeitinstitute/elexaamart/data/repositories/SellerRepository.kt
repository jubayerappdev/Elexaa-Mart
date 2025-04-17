package com.creativeitinstitute.elexaamart.data.repositories

import android.net.Uri
import com.creativeitinstitute.elexaamart.core.Nodes
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.data.models.UserLogin
import com.creativeitinstitute.elexaamart.data.models.UserRegister
import com.creativeitinstitute.elexaamart.services.AuthService
import com.creativeitinstitute.elexaamart.services.SellerService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class SellerRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) : SellerService {
    override fun uploadProductImage(productImageUri: Uri) : UploadTask {

        val storage: StorageReference = storageRef.child("products").child("PRD_${System.currentTimeMillis()}")

     return storage.putFile(productImageUri)

    }

    override fun uploadProduct(product: Product): Task<Void> {

        return db.collection(Nodes.PRODUCTS).document(product.productID).set(product)
    }


}