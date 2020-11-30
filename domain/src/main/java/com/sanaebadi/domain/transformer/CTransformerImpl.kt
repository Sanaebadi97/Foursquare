package com.sanaebadi.domain.transformer

import com.sanaebadi.domain.executor.PostExecutionThread
import com.sanaebadi.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CTransformerImpl @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : CTransformer() {

    override fun apply(upstream: Completable): CompletableSource =
        upstream.subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())

}