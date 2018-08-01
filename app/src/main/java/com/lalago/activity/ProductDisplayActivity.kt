package com.lalago.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.lalago.R
import com.lalago.adapter.ProductFragmentAdapter
import com.lalago.frament.ProductAllFragment
import com.lalago.frament.ProductBaseFragment
import com.lalago.frament.ProductManFragment
import com.lalago.frament.ProductWomenFragment
import com.lalago.retrofit.RetrofigConfig
import com.lalago.retrofit.RetrofitHelper
import kotlinx.android.synthetic.main.activity_product_display.*

import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by tangxutao on 2018/7/30.
 */

class ProductDisplayActivity : AppCompatActivity(){
    private val mDefaultSize = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_display)

        //init Fresco
        Fresco.initialize(this)

        //init retrofit help
        RetrofitHelper.init(RetrofigConfig.getInstance(this))

        //set fragments
        initView()
    }

    private fun initView() {
        val fragments = ArrayList<ProductBaseFragment>()

        fragments.add(ProductManFragment())
        fragments.add(ProductAllFragment())
        fragments.add(ProductWomenFragment())

        val adapter = ProductFragmentAdapter(supportFragmentManager, fragments)
        lalago_view_pager.adapter = adapter
        lalago_view_pager.offscreenPageLimit = mDefaultSize
        lalago_tab_layout.setupWithViewPager(lalago_view_pager)
    }
}
