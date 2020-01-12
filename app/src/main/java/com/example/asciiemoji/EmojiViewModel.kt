package com.example.asciiemoji

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asciiemoji.model.Emoji
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */
class EmojiViewModel : ViewModel() {

    private val mEmojies: MutableLiveData<List<Emoji>> = MutableLiveData()
    private val mError: MutableLiveData<String> = MutableLiveData()

    private val mRepository: EmojiRepository = EmojiRepository()

    fun loadEmojies(context: Context) {
        mRepository.getAllEmojies(context)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Emoji>>() {
                override fun onSuccess(emojies: List<Emoji>) {
                    mEmojies.postValue(emojies)
                }

                override fun onError(e: Throwable) {
                    mError.postValue(e.localizedMessage)
                }
            })
    }
}