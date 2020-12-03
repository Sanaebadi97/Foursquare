package com.sanaebadi.foursquare.ui.adapter

import android.view.View
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.ui.adapter.base.BaseViewHolder
import com.sanaebadi.foursquare.ui.adapter.base.VenueAction
import com.sanaebadi.foursquare.ui.adapter.base.ViewTypeHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.venue_row_item.view.*


open class VenueViewHolder(override val containerView: View) :
    BaseViewHolder<VenueObject>(containerView),
    LayoutContainer {

    override fun getType(): Int = ViewTypeHolder.VENUE

    override fun bind(data: VenueObject?) {
        data?.let {
            containerView.text_venue_name.text = it.name
            containerView.text_venue_address.text = it.address
            containerView.setOnClickListener { mSubject.onNext(VenueAction(data.id)) }
        }
    }
}