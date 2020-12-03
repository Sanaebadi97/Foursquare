package com.sanaebadi.foursquare.presentaion.ui.viewModel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanaebadi.foursquare.presentaion.exception.ExceptionHandler
import com.sanaebadi.foursquare.presentaion.exception.Exceptionhrowable
import com.sanaebadi.foursquare.presentaion.model.MessageModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException

abstract class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val taggedDisposables = mutableMapOf<String, Disposable>()
    val networkError = MutableLiveData<Int>()
    val messageObservable: MutableLiveData<MessageModel> = MutableLiveData()

    private fun Disposable.unTrack() {
        disposable.remove(this)
    }

    protected fun Disposable.track(tag: String? = null): Disposable {
        disposable.add(this)
        tag?.let {
            dispose(it)
            taggedDisposables[it] = this
        }
        return this
    }

    private fun dispose(tag: String) {
        taggedDisposables[tag]?.let {
            it.unTrack()
            it.dispose()
            taggedDisposables.remove(tag)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun logError(error: Throwable, errorCode: Int?) {
        val errors = mutableListOf<String>()
        if (error is CompositeException) {
            errors.addAll(error.exceptions.map {
                it.toString()

            })
        } else {
            errors.add(
                error.toString()
            )
        }
    }

    private fun handleError(error: Throwable) {

        val errorCode: Int? = if (error is Exceptionhrowable)
            error.code
        else
            null
        val errorMessage: String? = ExceptionHandler.getError(error)

        logError(error, errorCode)

        messageObservable.value = MessageModel(message = errorMessage)
        ExceptionHandler.isNetworkError(error)?.let { networkError.value = it }
    }

    protected fun <T> Single<T>.onError(): Single<T> =
        this.doOnError(::handleError)

    protected fun Completable.onError(): Completable =
        this.doOnError(::handleError)

    protected fun <T> Flowable<T>.onError(): Flowable<T> =
        this.doOnError(::handleError)
}