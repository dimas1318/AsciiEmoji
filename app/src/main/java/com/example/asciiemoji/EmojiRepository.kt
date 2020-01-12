package com.example.asciiemoji

import android.content.Context
import com.example.asciiemoji.model.Emoji
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */
class EmojiRepository {

    fun getAllEmojies(context: Context): Single<List<Emoji>> {
        return Single.fromCallable<List<Emoji>> {
            val result: MutableList<Emoji> = mutableListOf()
            try {
                val obj = JSONObject(loadJSONFromAsset(context))
                val emojies: JSONArray = obj.getJSONArray(EMOJIES_TAG)
                for (i in 0 until emojies.length()) {
                    val emoji = emojies.getJSONObject(i)
                    val words = emoji.getJSONArray(WORDS_TAG)
                    val word = words[0] as String
                    val ascii = emoji.getString(ASCII_TAG)
                    result.add(Emoji(word, ascii))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            result
        }.subscribeOn(Schedulers.io())
    }

    private fun loadJSONFromAsset(context: Context): String? {
        return try {
            val inputStream = context.assets.open(FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private companion object {
        const val FILE_NAME = "emojies.json"
        const val EMOJIES_TAG = "emojies"
        const val WORDS_TAG = "word"
        const val ASCII_TAG = "ascii"
    }
}