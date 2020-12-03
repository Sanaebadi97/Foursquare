package com.sanaebadi.foursquare.presentaion.exception

import com.google.gson.Gson
import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ExceptionHandler {

    fun convert(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> {
                parseError(throwable.response().errorBody()?.string())
            }
            is SocketTimeoutException -> {
                Exceptionhrowable(ExceptionCode.ERROR_TIMEOUT, ExceptionCode.ERROR_TIMEOUT_MESSAGE)
            }
            is IOException -> {
                Exceptionhrowable(ExceptionCode.ERROR_IO, ExceptionCode.ERROR_IO_MESSAGE)
            }
            else -> {
                Exceptionhrowable(ExceptionCode.ERROR_HAPPENED, ExceptionCode.ERROR_HAPPENED_MESSAGE)
            }
        }
    }

    fun getError(throwable: Throwable): String? {
        var error: String = ExceptionCode.ERROR_HAPPENED_MESSAGE

        if (throwable is CompositeException && throwable.exceptions.size > 1) {
            throwable.exceptions.forEach { exception ->
                if (exception is Exceptionhrowable && !exception.message.isNullOrEmpty())
                    error = exception.message!!
            }
        } else if (!throwable.message.isNullOrEmpty()) {
            error = when (throwable) {
                is Exceptionhrowable -> throwable.message!!
                else -> ExceptionCode.ERROR_HAPPENED_MESSAGE
            }
        }
        return error
    }

    fun parseError(errorBody: String?): Exceptionhrowable {
        return try {
            val result = Gson().fromJson(errorBody, ExceptionDataModel::class.java)
            Exceptionhrowable(result.exceptionError.errorCode, result.exceptionError.errorMessage!!)
        } catch (e: Exception) {
            Exceptionhrowable(ExceptionCode.ERROR_HAPPENED, ExceptionCode.ERROR_HAPPENED_MESSAGE)
        }
    }

    fun isNetworkError(throwable: Throwable): Int? {
        if (throwable is CompositeException)
            throwable.exceptions.forEach {
                if (
                    it is Exceptionhrowable &&
                    (it.code == ExceptionCode.ERROR_IO || it.code == ExceptionCode.ERROR_TIMEOUT)
                ) return it.code
            }
        if (throwable is Exceptionhrowable && (throwable.code == ExceptionCode.ERROR_IO || throwable.code == ExceptionCode.ERROR_TIMEOUT))
            return throwable.code

        return null
    }

    fun isSerpEmptyState(throwable: Throwable): Boolean {
        if (throwable is CompositeException)
            throwable.exceptions.forEach {
                if (it is Exceptionhrowable && it.code == ExceptionCode.ERROR_EMPTY_STATE)
                    return true
            }
        if (throwable is Exceptionhrowable && throwable.code == ExceptionCode.ERROR_EMPTY_STATE)
            return true

        return false
    }

    fun isUnauthenticated(throwable: Throwable): Boolean {
        if (throwable is HttpException && throwable.code() == 401) {
            return true
        } else if (throwable is CompositeException) {
            throwable.exceptions.forEach {
                if (it is HttpException && it.code() == 401)
                    return true
            }
        }

        return false
    }
}