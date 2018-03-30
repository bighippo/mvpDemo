package com.mercari.view

import com.mercari.model.ProductModel


interface ProductWomenView : MvpView {
    fun onSuccess(model: List<ProductModel>)
    fun onError(result: String)
}
