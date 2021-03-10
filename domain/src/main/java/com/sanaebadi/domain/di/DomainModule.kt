package com.sanaebadi.domain.di

import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.domain.transformer.*
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun completableTransformer(
        transformer: CTransformerImpl
    ): CTransformer

    @Binds
    abstract fun flowableTransformer(
        transformer: FTransformerImpl<List<VenueObject>>
    ): FTransformer<List<VenueObject>>

    @Binds
    abstract fun singleTransformer(
        transformer: STransformerImpl<Boolean>
    ): STransformer<Boolean>

    @Binds
    abstract fun flowableTransformerVenueObject(
        transformer: FTransformerImpl<VenueObject>
    ): FTransformer<VenueObject>
}