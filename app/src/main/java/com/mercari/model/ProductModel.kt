package com.mercari.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by tangxutao on 2018/3/29.
 */
class ProductModel {

    @Expose
    @SerializedName("id")
    var id: String = ""

    @Expose
    @SerializedName("name")
    var name: String = ""

    @Expose
    @SerializedName("status")
    var status: String = ""

    @Expose
    @SerializedName("num_likes")
    var num_likes: Int = 0

    @Expose
    @SerializedName("num_comments")
    var num_comments: Int = 0

    @Expose
    @SerializedName("price")
    var price: Int = 0

    @Expose
    @SerializedName("photo")
    var photo: String = ""
}