package com.sanaebadi.domain.transformer

import com.sanaebadi.domain.executor.PostExecutionThread
import com.sanaebadi.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher
import javax.inject.Inject

class FTransformerImpl<T> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : FTransformer<T>() {

    override fun apply(upstream: Flowable<T>): Publisher<T> =
        upstream.subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())

}