package com.lalago.view

import com.lalago.model.ProductModel

/**
 * Created by tangxutao on 2018/7/30.
 */
interface ProductAllView : MvpView {
    fun onSuccess(model: List<ProductModel>)
    fun onError(result: String)
}
