package util.network

import com.example.tatvasofttest.Extention.AppLog
import com.example.tatvasofttest.Response.BaseResponse
import com.example.tatvasofttest.network.Resource
import retrofit2.Response
import java.lang.Exception

class ResourceDataSoruce {

    open suspend fun <T> getResult(
        isInit: Boolean = false,
        call: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            val response = call()
            AppLog.e("responseCode", "isSuccessful => ${response.code()}")
            if (response.code() == 403) {
                return Resource.error("", code = response.code())
            } else if (response.code() == 502) {
                return Resource.error("", code = response.code())
            } else if (response.code() != 200) {
                return Resource.error(
                    "Something went wrong, please try after sometime",
                    code = response.code()
                )
            } else if (response.body() != null) {
                val baseResponse = (response.body() as BaseResponse)

                if (response.isSuccessful && baseResponse.status) {
                    response.body()?.let {
                        return Resource.success(it, baseResponse.message)
                    }
                } else {
                    if (isInit) {
                        response.body()?.let {
                            return Resource.success(it, baseResponse.message)
                        }
                    } else {
                        val body = response.body()

                        if (body != null) {
                            return Resource.error(baseResponse.message)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            return Resource.error("Error => ${e.message} ?: ${e.toString()}")
        }
        return Resource.error("Internet Connection Issue")
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message)
    }

}