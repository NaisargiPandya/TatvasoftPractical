package Extention

import android.content.Context
import android.widget.Toast

fun Context.showToast() {
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}