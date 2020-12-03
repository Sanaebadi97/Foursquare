package com.sanaebadi.domain.interactors

import com.sanaebadi.domain.interactors.base.CompletableUseCase
import com.sanaebadi.domain.model.ExploreVenuesRequestObject
import com.sanaebadi.domain.repository.VenuesRepository
import com.sanaebadi.domain.transformer.CTransformer
import io.reactivex.Completable
import javax.inject.Inject

class ExploreVenuesUseCase @Inject constructor(
    private val repository: VenuesRepository,
    private val transformer: CTransformer
) : CompletableUseCase<ExploreVenuesRequestObject>() {
    override fun execute(param: ExploreVenuesRequestObject): Completable =
        repository.exploreVenues(param).compose(transformer)
}