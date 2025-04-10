package com.creativeitinstitute.elexaamart.views.dashboard.seller.upload


import android.Manifest
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult

import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.core.areAllPermissionsGranted
import com.creativeitinstitute.elexaamart.core.extract
import com.creativeitinstitute.elexaamart.core.requestPermission
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.databinding.FragmentUploadProductBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UploadProductFragment : BaseFragment<FragmentUploadProductBinding>(
    FragmentUploadProductBinding::inflate
) {
    override fun setListener() {

        permissionsRequest = getPermissionRequest()


        binding.apply {
            ivProduct.setOnClickListener {

                requestPermission(permissionsRequest, permissionList)
            }

            btnUploadProduct.setOnClickListener {

                val name = etProductName.extract()
                val price = etProductPrice.extract()
                val description = etProductDescription.extract()
                val amount = etProductAmount.extract()

                val product = Product(
                    name = name,
                    description = description,
                    price = price.toDouble(),
                    amount = amount.toInt()
                )

                uploadProduct(product)
            }
        }


    }

    private fun getPermissionRequest(): ActivityResultLauncher<Array<String>> {

        return registerForActivityResult (ActivityResultContracts.RequestMultiplePermissions()){

            if (areAllPermissionsGranted(permissionList)){

                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Not Granted", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun uploadProduct(product: Product) {

    }

    override fun allObserver() {

    }

    companion object{
        private val permissionList = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    private lateinit var permissionsRequest: ActivityResultLauncher<Array<String>>


}