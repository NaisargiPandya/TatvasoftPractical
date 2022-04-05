package com.example.tatvasofttest.network

interface ApiConst {

    companion object {

        //http://sd2-hiring.herokuapp.com/api/users?offset=10&limit=10/

        var BASE_URL: String = "http://sd2-hiring.herokuapp.com/api/"

        val WEB_API_GET_MAIN_LIST = BASE_URL.plus("users?offset=10&limit=10")

    }

}
