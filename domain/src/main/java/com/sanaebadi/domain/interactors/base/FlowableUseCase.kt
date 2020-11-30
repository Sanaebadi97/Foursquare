package com.sanaebadi.domain.interactors.base

import io.reactivex.Flowable

abstract class FlowableUseCase<S, T> {

    operator fun invoke(params: T?): Flowable<S> {
        return if (params != null) {
            execute(params)
        } else {
            Flowable.error(IllegalArgumentException())
        }
    }

    protected abstract fun execute(params: T): Flowable<S>

}

operator fun <S> FlowableUseCase<S, Unit>.invoke(): Flowable<S> = this(Unit)
