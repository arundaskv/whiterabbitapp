package com.whiterabbit.app.ui.main

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whiterabbit.app.R
import com.whiterabbit.app.models.ContentItem


class CarouselViewPagerAdapter(var context: Context?,var contentList:List<ContentItem>) : PagerAdapter() {



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(
            R.layout.layout_carousel_item, container,
            false)
        container.addView(item)

        val productImage: ImageView = item.findViewById(R.id.carousel_image)

        if (!TextUtils.isEmpty(contentList[position].url)) {
            context?.let {
                Glide.with(it)
                    .load(contentList[position].url)
                    .apply(RequestOptions().
                        centerCrop().
                        placeholder(R.drawable.placeholder).
                        error(R.drawable.placeholder))
                    .into(productImage)
            }
        }else{
            productImage.setImageResource(R.drawable.placeholder)
        }

        return item
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return contentList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}