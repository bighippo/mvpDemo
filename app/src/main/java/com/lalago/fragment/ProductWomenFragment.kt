package com.lalago.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lalago.R
import com.lalago.adapter.ProductAdapter
import com.lalago.model.ProductModel
import com.lalago.presenter.ProductWomenPresenter
import com.lalago.util.GridSpacingItemDecoration
import com.lalago.view.ProductWomenView
import kotlinx.android.synthetic.main.fragment_product.*

/**
 * Created by tangxutao on 2018/7/30.
 */
class ProductWomenFragment : ProductBaseFragment() {
    private lateinit var mWomenModels : List<ProductModel>
    private var mWomenPrenster = ProductWomenPresenter()
    private val mColumn = 2
    private var mLastPosition = 0

    override fun getFragmentName(): String {
        return "Women"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mWomenPrenster.attachView(mWomenView)

        initData()
    }

    private fun initView() {
        val adapter = ProductAdapter(activity as Context, mWomenModels)

        //you can add different layoutManager to realize different feature
        val layoutManager = GridLayoutManager(activity, mColumn)

        lalago_recycler_view.adapter = adapter

        lalago_recycler_view.layoutManager = layoutManager

        val gap = activity!!.resources.getDimensionPixelSize(R.dimen.gird_itme_gap)

        lalago_recycler_view.addItemDecoration(GridSpacingItemDecoration(mColumn, gap,true))

        adapter.setOnRecyclerItemClick(object : ProductAdapter.OnRecyclerItemClick {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        lalago_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                mLastPosition = layoutManager.findLastVisibleItemPosition()
            }

            //override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //super.onScrolled(recyclerView, dx, dy)
            //}
        })
    }

    private var mWomenView = object : ProductWomenView {
        override fun onError(result: String) {
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(model: List<ProductModel>) {
            mWomenModels = model

            initView()
        }
    }

    private fun initData(){
        mWomenPrenster.getWomenProduct()
    }
}