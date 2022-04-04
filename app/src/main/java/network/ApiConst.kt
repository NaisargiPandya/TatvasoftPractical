package network

interface ApiConst {

    companion object {
        var BASE_URL: String = "http://sd2-hiring.herokuapp.com/api/"

        const val WEB_API_GET_MAIN_LIST = "users?offset=10&limit=10/"

    }

}
