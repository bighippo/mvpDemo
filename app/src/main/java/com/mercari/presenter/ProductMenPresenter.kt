package com.mercari.presenter

import android.util.Log
import com.mercari.model.ProductModel
import com.mercari.restapi.ProductApi
import com.mercari.retrofit.RetrofitHelper
import com.mercari.view.MvpView
import com.mercari.view.ProductMenView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tangxutao on 2018/3/29.
 */
class ProductMenPresenter : MvpPresenter{
    var productApi: ProductApi?= null

    var mManView: ProductMenView? = null

    constructor(){
        productApi = RetrofitHelper.createApi(ProductApi::class.java)
    }

    override fun attachView(view: MvpView) {
        mManView = view as ProductMenView
    }

    override fun onCreate() {

    }

    override fun onStop() {

    }

    fun getMenProduct() {
        var observable = productApi!!.getMenProducts()

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<ProductModel>> {
                    override fun onComplete() {
                        Log.d("abcd", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("abcd", "onSubscribe")
                    }

                    override fun onNext(t: List<ProductModel>) {
                        mManView!!.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        mManView!!.onError(e.toString())
                    }
                })
    }
}