package com.sanaebadi.foursquare.mapper

import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.entity.VenueEntity

fun VenueEntity.mapEntityToObject() = VenueObject(
    id = id,
    name = name,
    address = address,
    contact = contact,
    categoryIcon = categoryIcon,
    website = website,
    distance = distance,
    categoryName = categoryName
)