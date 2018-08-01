package com.lalago.presenter

import com.lalago.model.ProductModel
import com.lalago.restapi.ProductApi
import com.lalago.retrofit.RetrofitHelper
import com.lalago.view.MvpView
import com.lalago.view.ProductMenView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tangxutao on 2018/7/30.
 */
class ProductMenPresenter : MvpPresenter{
    private var mProductApi: ProductApi?= null

    private var mManView: ProductMenView? = null

    constructor(){
        mProductApi = RetrofitHelper.createApi(ProductApi::class.java)
    }

    override fun attachView(view: MvpView) {
        mManView = view as ProductMenView
    }

    override fun onCreate() {

    }

    override fun onStop() {

    }

    fun getMenProduct() {
        val observable = mProductApi!!.getMenProducts()

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<ProductModel>> {
                    override fun onComplete() {
                        //Log.d("abcd", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        //Log.d("abcd", "onSubscribe")
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