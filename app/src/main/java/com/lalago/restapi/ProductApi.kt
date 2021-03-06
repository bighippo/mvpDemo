package com.lalago.restapi

import com.lalago.model.ProductModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by tangxutao on 2018/7/30.
 */
interface ProductApi {


    /**
    * GET MEN'S PRODUCT
    *
    * @ return
    */

    @GET("men.json")
    fun getMenProducts() : Observable <List<ProductModel>>


    /**
     * GET WOMEN'S PRODUCT
     *
     * @return
     */

    @GET("women.json")
    fun getWomenProducts(): Observable<List<ProductModel>>


    /**
     * GET ALL PRODUCT
     *
     * @return
     */

    @GET("all.json")
    fun getAllProducts(): Observable<List<ProductModel>>
}