package com.example.catapp.remote.data

import com.google.gson.annotations.SerializedName

data class CatModel (
    @SerializedName("id"    ) var id     : String?           = null,
    @SerializedName("url"   ) var url    : String?           = null,
    @SerializedName("width" ) var width  : Int?              = null,
    @SerializedName("height") var height : Int?              = null
)