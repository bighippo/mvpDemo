package com.mercari.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.mercari.R
import com.mercari.adapter.ProductFragmentAdapter
import com.mercari.frament.ProductAllFragment
import com.mercari.frament.ProductBaseFragment
import com.mercari.frament.ProductManFragment
import com.mercari.frament.ProductWomenFragment
import com.mercari.retrofit.RetrofigConfig
import com.mercari.retrofit.RetrofitHelper
import kotlinx.android.synthetic.main.activity_product_display.*


class ProductDisplayActivity : AppCompatActivity() , ProductBaseFragment.onRecyclerItemClick{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_display)

        //init Fresco
        Fresco.initialize(this);

        //init retrofit help
        RetrofitHelper.init(this, RetrofigConfig.getInstance(this))

        initView()
    }

    fun initView() {
        val fragments = ArrayList<ProductBaseFragment>()

        fragments.add(ProductManFragment())
        fragments.add(ProductAllFragment())
        fragments.add(ProductWomenFragment())

        val adapter = ProductFragmentAdapter(supportFragmentManager, fragments)
        mercari_view_pager.setAdapter(adapter)
        mercari_view_pager.setOffscreenPageLimit(2)
        mercari_tab_layout.setupWithViewPager(mercari_view_pager)
    }

    override fun onRecyclerItemClick(message: Int) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
    }
}
