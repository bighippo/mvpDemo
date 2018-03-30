package com.mercari.frament

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mercari.R
import com.mercari.adapter.ProductAdapter
import com.mercari.model.ProductModel
import com.mercari.presenter.ProductWomenPresenter
import com.mercari.util.GridSpacingItemDecoration
import com.mercari.view.ProductWomenView
import kotlinx.android.synthetic.main.fragment_product.*

/**
 * Created by tangxutao on 2018/3/28.
 */
class ProductWomenFragment : ProductBaseFragment() {
    private var mListener: onRecyclerItemClick? = null
    private lateinit var womenModels : List<ProductModel>
    var womenPrenster = ProductWomenPresenter()
    val COLUMN = 2
    var beforePosition = 0
    var lastPosition = 0
    val OFFSET = 0

    override fun getFragmentName(): String {
        return "Women"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_product, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        womenPrenster.attachView(mWomenView)

        initData()
    }

    private fun initView() {
        var adapter = ProductAdapter(getActivity() as Context,womenModels)

        //you can add different layoutManager to realize different feature
        val layoutManager = GridLayoutManager(getActivity(), COLUMN)

        mercari_recycler_view.adapter = adapter

        mercari_recycler_view.layoutManager = layoutManager

        var gap = activity!!.resources.getDimensionPixelSize(R.dimen.gird_itme_gap)

        mercari_recycler_view.addItemDecoration(GridSpacingItemDecoration(COLUMN,gap,true))

        adapter.setOnRecyclerItemClick(object : ProductAdapter.onRecyclerItemClick {
            override fun onItemClick(view: View, position: Int) {
                mListener!!.onRecyclerItemClick(position)
            }
        })

        mercari_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (layoutManager is GridLayoutManager) {

                    lastPosition = layoutManager.findLastVisibleItemPosition()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        fab_button.setOnClickListener{
            if (beforePosition == lastPosition) {
                lastPosition = lastPosition + 6
            }

            (mercari_recycler_view.layoutManager as GridLayoutManager).scrollToPositionWithOffset(lastPosition, OFFSET)
            beforePosition = lastPosition
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mListener = activity as onRecyclerItemClick
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + "must implement onRecyclerItemClick")
        }
    }

    var mWomenView = object : ProductWomenView {
        override fun onError(result: String) {
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(model: List<ProductModel>) {
            womenModels = model

            initView()
        }
    }

    fun initData(){
        womenPrenster.getWomenProduct()
    }
}