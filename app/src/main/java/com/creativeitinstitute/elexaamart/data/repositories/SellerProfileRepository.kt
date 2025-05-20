package com.creativeitinstitute.elexaamart.data.repositories

import android.net.Uri
import com.creativeitinstitute.elexaamart.core.Nodes
import com.creativeitinstitute.elexaamart.views.dashboard.seller.profile.Profile
import com.creativeitinstitute.elexaamart.views.dashboard.seller.profile.toMap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class SellerProfileRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) {
    fun uploadImage(profileImageUri: Uri) : UploadTask {

        val storage: StorageReference = storageRef.child("profile").child("USER_${System.currentTimeMillis()}")

     return storage.putFile(profileImageUri)

    }

    fun updateUser(user: Profile): Task<Void> {

        return db.collection(Nodes.USER).document(user.userID).update(user.toMap())
    }

    fun getUserByUserID(userID: String) : Task<QuerySnapshot> {

      return  db.collection(Nodes.USER).whereEqualTo("userID", userID).get()

    }


}