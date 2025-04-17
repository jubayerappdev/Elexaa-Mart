package com.creativeitinstitute.elexaamart.services

import android.net.Uri
import com.creativeitinstitute.elexaamart.data.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask

interface SellerService {

    fun uploadProductImage(productImageUri: Uri): UploadTask

    fun uploadProduct(product: Product): Task<Void>

}