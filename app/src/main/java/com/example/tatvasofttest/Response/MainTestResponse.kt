package com.example.tatvasofttest.Response

import com.google.gson.annotations.SerializedName

data class MainTestResponse(

    @SerializedName("data")
    var data: DataList,

) : BaseResponse() {

    data class DataList(

        @SerializedName("users")
        val users: List<Users>?,

        @SerializedName("has_more")
        val has_more: String,

    ) {
        data class Users(

            @SerializedName("name")
            val name: String,

            @SerializedName("image")
            val image: String,

            @SerializedName("items")
            val items: List<String>,

            )
    }


}




