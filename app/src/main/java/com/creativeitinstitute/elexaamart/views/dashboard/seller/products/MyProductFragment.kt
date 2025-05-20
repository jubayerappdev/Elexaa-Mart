package com.creativeitinstitute.elexaamart.views.dashboard.seller.products

import androidx.fragment.app.viewModels
import com.creativeitinstitute.elexaamart.base.BaseFragment
import com.creativeitinstitute.elexaamart.core.DataState
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.databinding.FragmentMyProductBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProductFragment : BaseFragment<FragmentMyProductBinding>(FragmentMyProductBinding::inflate) {


    private val viewModel : ProductViewModel by viewModels()


    override fun setListener() {

        FirebaseAuth.getInstance().currentUser?.let {
            viewModel.getProductByID(it.uid)
        }


    }

    override fun allObserver() {

        viewModel.productResponse.observe(viewLifecycleOwner) {
            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                }
                is DataState.Loading-> {
                    loading.show()
                }
                is DataState.Success -> {
                    it.data?.let { it1->
                        setDataToRV(it1)
                    }
                    loading.dismiss()
                }
            }
        }

    }
    private fun setDataToRV(it: List<Product>) {

        binding.rvSeller.adapter = SellerProductAdapter(it)

    }

}
