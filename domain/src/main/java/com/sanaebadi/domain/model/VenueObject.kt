package com.sanaebadi.domain.model
data class VenueObject(
    val id: String,
    val name: String,
    val address: String?,
    val contact: String?,
    val categoryIcon: String?,
    val website: String?,
    val distance: Int?,
    val categoryName: String?
) : DomainModel
