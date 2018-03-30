

package com.mercari.presenter

import com.mercari.view.MvpView

interface MvpPresenter {
    fun onCreate()

    fun onStop()

    fun attachView(view: MvpView)
}
