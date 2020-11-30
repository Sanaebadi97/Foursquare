package com.sanaebadi.foursquare.mapper

import com.sanaebadi.foursquare.entity.VenueEntity
import com.sanaebadi.foursquare.entity.dto.Venue

fun Venue.mapDomainToEntity() = VenueEntity(
    0,
    id = id,
    name = name,
    address = this.location?.formattedAddress?.joinToString(","),
    contact = this.contact?.phone,
    categoryIcon = this.categories?.firstOrNull()?.let { "${it.icon?.prefix}${it.icon?.suffix}" },
    website = url,
    distance = location?.distance,
    categoryName = this.categories?.firstOrNull()?.let { it.name }
)