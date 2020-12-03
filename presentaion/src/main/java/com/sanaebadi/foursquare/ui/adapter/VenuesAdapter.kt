package com.sanaebadi.foursquare.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.ui.adapter.base.*

open class VenuesAdapter(
    private val listener: (holder: BaseViewHolder<*>) -> Unit
) : BaseRecyclerViewAdapter(CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        val holder = when (viewType) {
            ViewTypeHolder.VENUE -> VenueViewHolder(view)
            ViewTypeHolder.LOAD_MORE_VIEW -> LoadMoreViewHolder(view)
            else -> EmptyViewHolder(view)
        }

        listener.invoke(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        super.onBindViewHolder(holder, position)

        when (holder.getType()) {
            ViewTypeHolder.VENUE -> (holder as VenueViewHolder).bind(mItems[position] as? VenueObject)
            ViewTypeHolder.LOAD_MORE_VIEW -> (holder as LoadMoreViewHolder).bind(Unit)
        }
    }


    companion object {
        val CONFIG = Config.Builder()
            .setPreFetch(10)
            .setScreenSize(10)
            .build()
    }
}