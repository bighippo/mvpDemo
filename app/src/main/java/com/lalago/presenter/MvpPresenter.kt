

package com.lalago.presenter

import com.lalago.view.MvpView

/**
 * Created by tangxutao on 2018/7/30.
 */

interface MvpPresenter {
    fun onCreate()

    fun onStop()

    fun attachView(view: MvpView)
}
