package com.sanaebadi.foursquare.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aroundVenue")
data class VenueEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "venue_id")
    val venueId: Int,
    val id: String,
    val name: String,
    val address: String?,
    val contact: String?,
    @ColumnInfo(name = "category_icon")
    val categoryIcon: String?,
    val website: String?,
    val distance: Int?,
    val categoryName: String?
)