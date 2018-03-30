package com.mercari.presenter

import android.util.Log
import com.mercari.model.ProductModel
import com.mercari.restapi.ProductApi
import com.mercari.retrofit.RetrofitHelper
import com.mercari.view.MvpView
import com.mercari.view.ProductWomenView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tangxutao on 2018/3/29.
 */
class ProductWomenPresenter : MvpPresenter{
    var productApi: ProductApi?= null
    var mWomenView : ProductWomenView ?= null

    constructor(){
       productApi = RetrofitHelper.createApi(ProductApi::class.java)
    }

    override fun attachView(view: MvpView) {
        mWomenView = view as ProductWomenView
    }

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getWomenProduct() {
        var observable = productApi!!.getWomenProducts()

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
                        mWomenView!!.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("abcd","onError:"+e.toString());
                    }
                })
    }
}