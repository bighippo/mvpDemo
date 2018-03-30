package com.mercari.view

import com.mercari.model.ProductModel


interface ProductMenView : MvpView {
    fun onSuccess(model: List<ProductModel>)
    fun onError(result: String)
}
