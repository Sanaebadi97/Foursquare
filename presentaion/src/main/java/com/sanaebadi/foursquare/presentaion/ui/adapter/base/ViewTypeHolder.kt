package com.sanaebadi.foursquare.presentaion.ui.adapter.base

import com.sanaebadi.domain.model.DomainModel
import com.sanaebadi.domain.model.LoadMoreObject
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.presentaion.R


object ViewTypeHolder {

    val VENUE: Int = R.layout.venue_row_item
    val LOAD_MORE_VIEW: Int = R.layout.load_more

    fun getView(obj: DomainModel?): Int {
        if (obj == null) return 0
        return when (obj::class) {
            VenueObject::class -> VENUE
            LoadMoreObject::class -> LOAD_MORE_VIEW
            else -> 0
        }
    }
}
