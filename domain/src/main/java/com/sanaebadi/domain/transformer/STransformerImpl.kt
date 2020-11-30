package com.sanaebadi.domain.transformer

import com.sanaebadi.domain.executor.PostExecutionThread
import com.sanaebadi.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class STransformerImpl<T> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : STransformer<T>() {

    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())

}