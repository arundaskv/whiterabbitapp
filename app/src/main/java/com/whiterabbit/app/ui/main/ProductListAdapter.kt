package com.whiterabbit.app.ui.main

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whiterabbit.app.R
import com.whiterabbit.app.models.ContentItem
import kotlinx.android.synthetic.main.layout_product_list_item.view.*

class ProductListAdapter (var context: Context?,var contentItems : List<ContentItem>) : RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>()  {


    class ProductItemViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var productImage = item.productImage
        var productTitle = item.productTitle
        var productDeal = item.productDeal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_product_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        var content = contentItems[position]
        holder.productTitle.text = content.title
        holder.productDeal.text = content.deal
        var url = content.url
        if (!TextUtils.isEmpty(url)) {
            context?.let {
                Glide.with(it)
                    .load(url)
                    .apply(RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                    .into(holder.productImage)
            }
        }else{
            holder.productImage.setImageResource(R.drawable.placeholder)
        }
    }
}