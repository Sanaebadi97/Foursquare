package com.sanaebadi.domain.interactors

import com.sanaebadi.domain.interactors.base.FlowableUseCase
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.domain.repository.VenuesRepository
import com.sanaebadi.domain.transformer.FTransformer
import io.reactivex.Flowable
import javax.inject.Inject

class VenueDetailsUseCase @Inject constructor(
    private val repository: VenuesRepository,
    private val transformer: FTransformer<VenueObject>
) : FlowableUseCase<VenueObject, String>() {
    override fun execute(params: String): Flowable<VenueObject> =
        repository.getVenueById(params).compose(transformer)
}