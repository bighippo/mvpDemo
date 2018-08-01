package com.lalago.fragment

import android.support.v4.app.Fragment

/**
 * Created by tangxutao on 2018/7/30.
 */
abstract class ProductBaseFragment : Fragment() {
    abstract fun getFragmentName(): String
}