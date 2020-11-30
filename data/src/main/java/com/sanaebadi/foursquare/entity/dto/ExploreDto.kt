package com.sanaebadi.foursquare.entity.dto

data class ExploreDto(
    val response: GroupList
)

data class GroupList(
    val totalResults:Int,
    val groups: List<ItemDto>
)

data class ItemDto(
    val type: String,
    val name: String,
    val items: List<VenueItem>
)

data class VenueItem(
    val venue: Venue
)