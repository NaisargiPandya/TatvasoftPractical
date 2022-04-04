package Extention

import android.util.Log
import de.hdodenhof.circleimageview.BuildConfig

object AppLog {

    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun d(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg!!)
        }
    }
}