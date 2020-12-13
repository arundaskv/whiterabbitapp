package com.whiterabbit.app.ui.main

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whiterabbit.app.R
import com.whiterabbit.app.models.Page
import com.whiterabbit.app.models.WidgetItem
import com.whiterabbit.app.utils.Constants
import com.whiterabbit.app.utils.Constants.Companion.VIEW_TYPE_BANNER
import com.whiterabbit.app.utils.Constants.Companion.VIEW_TYPE_CAROUSEL
import com.whiterabbit.app.utils.Constants.Companion.VIEW_TYPE_NOT_DEFINED
import com.whiterabbit.app.utils.Constants.Companion.VIEW_TYPE_PRODUCT
import kotlinx.android.synthetic.main.layout_banner_widget.view.*
import kotlinx.android.synthetic.main.layout_carousel_widget.view.*
import kotlinx.android.synthetic.main.layout_not_defined_widget.view.*
import kotlinx.android.synthetic.main.layout_product_widget.view.*
import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager
import kotlin.random.Random

class WidgetListAdapter (var widgetItems : List<WidgetItem>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            VIEW_TYPE_BANNER ->{
                return BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_banner_widget, parent, false))
            }
            VIEW_TYPE_PRODUCT ->{
                return ProductListViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_product_widget, parent, false))
            }
            VIEW_TYPE_CAROUSEL ->{
                return CarouselViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_carousel_widget, parent, false))
            }
        }
        return NotDefinedViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_not_defined_widget, parent, false))
    }

    override fun getItemCount(): Int {
        return widgetItems.size
    }

    override fun getItemViewType(position: Int): Int {

        return getViewTypeBasedOnWidgetType(position)
    }

    private fun getViewTypeBasedOnWidgetType(position: Int): Int {
        when (widgetItems[position].type) {
            Constants.WIDGET_TYPE_BANNER -> {
                return VIEW_TYPE_BANNER
            }
            Constants.WIDGET_TYPE_CAROUSEL -> {
                return VIEW_TYPE_CAROUSEL
            }
            Constants.WIDGET_TYPE_PRODUCT -> {
                return VIEW_TYPE_PRODUCT
            }
        }
        return VIEW_TYPE_NOT_DEFINED;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            VIEW_TYPE_BANNER->{
                var viewHolder:BannerViewHolder = holder as BannerViewHolder

                val index = Random.nextInt(0,widgetItems[position].items.size)
                var url = widgetItems[position].items[index].url
                if (!TextUtils.isEmpty(url)) {
                        context?.let {
                            Glide.with(it)
                                .load(url)
                                .apply(RequestOptions().centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                                .into(viewHolder.banner_image)
                        }
                }else{
                    viewHolder.banner_image.setImageResource(R.drawable.placeholder)
                }

            }
            VIEW_TYPE_CAROUSEL->{
                var viewHolder:CarouselViewHolder = holder as CarouselViewHolder
                viewHolder.viewPager.adapter = CarouselViewPagerAdapter(context,widgetItems[position].items)
                viewHolder.viewPager.setInterval(3000)
                viewHolder.viewPager.setDirection(AutoScrollViewPager.Direction.RIGHT)
                viewHolder.viewPager.setCycle(true)
                viewHolder.viewPager.startAutoScroll()
                viewHolder.dots_indicator.setViewPager(viewHolder.viewPager)
            }
            VIEW_TYPE_PRODUCT->{
                var viewHolder:ProductListViewHolder = holder as ProductListViewHolder
                viewHolder.title.text = "Product 1"
            }
            VIEW_TYPE_NOT_DEFINED->{
                var viewHolder:NotDefinedViewHolder = holder as NotDefinedViewHolder
                viewHolder.title.text = "Not Defined 1"
            }
        }
    }


    class CarouselViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var viewPager = item.viewPager
        var dots_indicator = item.dots_indicator
    }

    class BannerViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var banner_image = item.banner_image
    }
    class ProductListViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var title = item.product_title
    }
    class NotDefinedViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var title = item.title
    }

    fun updatePage(page: Page?){
        if(page?.widget != null && page?.widget?.size?:0 > 0){
            this.widgetItems = page?.widget
           notifyDataSetChanged()
        }
    }

}