package com.sanaebadi.foursquare.entity.dto

data class Location(
    val address: String?,
    val crossStreet: String?,
    val lat: String?,
    val lng: String?,
    val distance: Int?,
    val postalCode: String?,
    val cc: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val formattedAddress: List<String>?
)