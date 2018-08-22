package com.example.android.instantappsample.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber

open class MyObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        Timber.e(e)
    }

}