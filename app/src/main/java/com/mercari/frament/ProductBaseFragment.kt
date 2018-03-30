package com.mercari.frament

import android.support.v4.app.Fragment

/**
 * Created by tangxutao on 2018/3/28.
 */
abstract class ProductBaseFragment : Fragment() {

    abstract fun getFragmentName(): String

    interface onRecyclerItemClick {
        fun onRecyclerItemClick(message: Int)
    }
}