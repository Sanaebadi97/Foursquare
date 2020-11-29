package com.sanaebadi.foursquare.repository.dataSourceImpl

import com.sanaebadi.foursquare.repository.dataSource.venues.VenuesDataSource
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    private val dataSource: VenuesDataSource
):