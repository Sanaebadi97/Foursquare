package com.sanaebadi.domain.interactors.base

import io.reactivex.Completable

abstract class CompletableUseCase<T> {

    operator fun invoke(params: T?): Completable {
        return if (params != null) {
            execute(params)
        } else {
            Completable.error(IllegalArgumentException())
        }
    }

    protected abstract fun execute(param: T): Completable
}

operator fun CompletableUseCase<Unit>.invoke(): Completable = this(Unit)