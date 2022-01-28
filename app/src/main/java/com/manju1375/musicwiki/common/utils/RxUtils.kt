package com.manju1375.musicwiki.common.utils

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {
    /**
     * Returns a transformer that applies scheduling on Single streams
     *
     * @param <T> The data type of the observable stream
     * @return A transformer that can apply the correct schedulers
    </T> */
    @JvmStatic
    fun <T: Any> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { single: Single<T> ->
            single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}