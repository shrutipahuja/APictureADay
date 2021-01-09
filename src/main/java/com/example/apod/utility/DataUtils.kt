package com.example.apod.utility

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset

@Suppress("JAVA_CLASS_ON_COMPANION")
class DataUtils {

    companion object {
        fun readJson(fileName: String): String {
            val inputStream = javaClass.classLoader!!
                .getResourceAsStream("imagedata/$fileName")
            return inputStream.readBytes().toString(Charset.defaultCharset())

        }

        inline fun <reified T> fromJson(json: String): T {
            return Gson().fromJson(json, object : TypeToken<T>() {}.type)
        }
    }
}