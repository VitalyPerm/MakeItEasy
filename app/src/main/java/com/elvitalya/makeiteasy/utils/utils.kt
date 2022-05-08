package com.elvitalya.makeiteasy.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


fun getJsonFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}






