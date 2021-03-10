package com.sanaebadi.domain.transformer

import io.reactivex.FlowableTransformer

abstract class FTransformer<T> : FlowableTransformer<T, T>