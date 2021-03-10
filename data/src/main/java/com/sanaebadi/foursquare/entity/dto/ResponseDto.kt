package com.sanaebadi.foursquare.entity.dto

data class ResponseDto(
    val response: VenuesList
)

data class VenuesList(
    val venues: List<Venue>
)