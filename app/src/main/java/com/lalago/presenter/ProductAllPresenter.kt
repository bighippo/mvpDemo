package com.lalago.presenter

import android.util.Log
import com.lalago.model.ProductModel
import com.lalago.restapi.ProductApi
import com.lalago.retrofit.RetrofitHelper
import com.lalago.view.MvpView
import com.lalago.view.ProductAllView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tangxutao on 2018/7/30.
 */
class ProductAllPresenter :MvpPresenter{
    private val TAG = "ProductAllPresenter"

    private var mProductApi: ProductApi?= null

    private var mAllView: ProductAllView? = null

    constructor(){
        mProductApi = RetrofitHelper.createApi(ProductApi::class.java)
    }

    override fun attachView(view: MvpView) {
        mAllView = view as ProductAllView
    }

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllProduct() {
        val observable = mProductApi!!.getAllProducts()

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<ProductModel>> {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe")
                    }

                    override fun onNext(t: List<ProductModel>) {
                        mAllView!!.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        mAllView!!.onError(e.toString())
                    }
                })
    }
}