package com.sanaebadi.foursquare.presentaion.ui.adapter.base

import com.sanaebadi.domain.model.DomainModel

interface RecyclerViewInterface {

    fun remove(index: Int)
    fun removeAll()
    fun <T : DomainModel> addItems(items: List<T>)


}