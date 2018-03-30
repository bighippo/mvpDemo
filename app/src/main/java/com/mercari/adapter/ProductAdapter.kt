package com.mercari.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mercari.R
import com.mercari.model.ProductModel
import kotlinx.android.synthetic.main.item_product_all.view.*
import kotlinx.android.synthetic.main.item_product_man.view.*
import kotlinx.android.synthetic.main.item_product_women.view.*

/**
 * Created by tangxutao on 2018/3/28.
 */

/**
 * Created by tangxutao on 2018/3/28.
 */
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHodler> {
    val PRODUCT_MAN = 1
    val PRODUCT_WOMEN = 2
    val PRODUCT_ALL = 3
    val SOLD_OUT = "sold_out"

    var mContext : Context

    private var mOnRecyclerItemClick: onRecyclerItemClick? = null
    var mModels : List<ProductModel>

    constructor(context: Context, modelList: List<ProductModel>) {
        mContext = context
        mModels = modelList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        when(viewType) {
            PRODUCT_MAN -> return ViewHodler(View.inflate(parent?.context, R.layout.item_product_man, null))
            PRODUCT_WOMEN -> return ViewHodler(View.inflate(parent?.context, R.layout.item_product_women, null))
            PRODUCT_ALL -> return ViewHodler(View.inflate(parent?.context, R.layout.item_product_all, null))
        }

        return ViewHodler(View.inflate(parent?.context, R.layout.item_product_man, null))
    }

    override fun getItemCount(): Int {
        return mModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return PRODUCT_MAN
    }

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        with(holder.itemView!!){
            when (getItemViewType(position)) {
                PRODUCT_MAN -> {
                    if (IsSoldOut(position))
                        iv_man_product_out.visibility = View.VISIBLE
                    else
                        iv_man_product_out.visibility = View.INVISIBLE

                    //display image
                    iv_man_product.setImageURI(mModels[position].photo)

                    tv_man_product.text = mModels[position].name

                    tv_man_comment_count.text = mModels[position].num_comments.toString()

                    tv_man_like_count.text = mModels[position].num_likes.toString()

                    tv_man_price.text = "$ " + mModels[position].price.toString()

                    //set image click event
                    iv_man_product.setOnClickListener{
                        if(mOnRecyclerItemClick != null)
                            mOnRecyclerItemClick!!.onItemClick(holder.itemView, position)
                    }

                }

                PRODUCT_WOMEN -> {
                    if (IsSoldOut(position))
                        iv_women_product_out.visibility = View.VISIBLE
                    else
                        iv_women_product_out.visibility = View.INVISIBLE

                    iv_women_product.setImageURI(mModels[position].photo)

                    tv_women_product.text = mModels[position].name

                    tv_women_comment_count.text = mModels[position].num_comments.toString()

                    tv_women_like_count.text = mModels[position].num_likes.toString()

                    tv_women_price.text = "$ " + mModels[position].price.toString()

                    iv_women_product.setOnClickListener{
                        if(mOnRecyclerItemClick != null)
                            mOnRecyclerItemClick!!.onItemClick(holder.itemView, position)
                    }
                }

                PRODUCT_ALL -> {
                    if (IsSoldOut(position))
                        iv_all_product_out.visibility = View.VISIBLE
                    else
                        iv_all_product_out.visibility = View.INVISIBLE

                    iv_all_product.setImageURI(mModels[position].photo)

                    tv_all_product.text = mModels[position].name

                    tv_all_comment_count.text = mModels[position].num_comments.toString()

                    tv_all_like_count.text = mModels[position].num_likes.toString()

                    tv_all_price.text = "$ " + mModels[position].price.toString()

                    iv_all_product.setOnClickListener{
                        if(mOnRecyclerItemClick != null)
                            mOnRecyclerItemClick!!.onItemClick(holder.itemView, position)
                    }
                }
            }
        }
    }

    fun IsSoldOut(position: Int):Boolean{
        if(mModels[position].status.equals(SOLD_OUT))
            return true
        else
            return false
    }


    class ViewHodler(item : View) : RecyclerView.ViewHolder(item)

    interface onRecyclerItemClick {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnRecyclerItemClick(mOnRecyclerItemClick: onRecyclerItemClick) {
        this.mOnRecyclerItemClick = mOnRecyclerItemClick
    }
}