package com.creativeitinstitute.elexaamart.views.dashboard.seller.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.creativeitinstitute.elexaamart.data.Product
import com.creativeitinstitute.elexaamart.databinding.ItemSellerProductBinding

class SellerProductAdapter(val productList: List<Product>) : RecyclerView.Adapter<SellerProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        return ProductViewHolder(ItemSellerProductBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
       productList[position].let {

           holder.binding.apply {
               textProductName.text = it.name
               textProductDescription.text = it.description
               textProductPrice.text = "Price: $${it.price}"
               textProductAmount.text = "Stock : ${it.amount}"

               ivProduct.load(it.imageLink)
           }
       }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    class ProductViewHolder (val binding: ItemSellerProductBinding) : RecyclerView.ViewHolder(binding.root)
}