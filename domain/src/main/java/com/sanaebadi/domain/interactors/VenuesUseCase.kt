package com.sanaebadi.domain.interactors

import com.sanaebadi.domain.interactors.base.FlowableUseCase
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.domain.repository.VenuesRepository
import com.sanaebadi.domain.transformer.FTransformer
import io.reactivex.Flowable
import javax.inject.Inject

class VenuesUseCase @Inject constructor(
    private val repository: VenuesRepository,
    private val transformer: FTransformer<List<VenueObject>>
) : FlowableUseCase<List<VenueObject>, Unit>() {
    override fun execute(params: Unit): Flowable<List<VenueObject>> =
        repository.getVenues().compose(transformer)
}