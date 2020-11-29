package com.sanaebadi.foursquare.entity.dto

data class Category(
    val id: String,
    val name: String,
    val pluralName: String?,
    val shortName: String?,
    val icon: Icon?,
    val primary: String?
)