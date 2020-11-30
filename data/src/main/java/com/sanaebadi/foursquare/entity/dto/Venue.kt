package com.sanaebadi.foursquare.entity.dto

data class Venue(
    val id: String,
    val name: String,
    val contact: Contact?,
    val location: Location?,
    val categories: List<Category>?,
    val url: String?
)