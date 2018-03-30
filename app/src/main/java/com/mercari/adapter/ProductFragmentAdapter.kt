package com.mercari.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mercari.frament.ProductBaseFragment


/**
 * Created by tangxutao on 2018/3/28.
 */
class ProductFragmentAdapter : FragmentPagerAdapter {

    var mFragments: List<ProductBaseFragment>? = null

    constructor(fm: FragmentManager, fragments: List<ProductBaseFragment> ) : super(fm) {
        mFragments = fragments
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        var ret = 0
        if (mFragments != null) {
            ret = mFragments!!.size
        }
        return ret
    }

    override fun getPageTitle(position: Int): CharSequence {

        return mFragments!![position].getFragmentName()
    }
}