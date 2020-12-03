package com.sanaebadi.foursquare.ui.adapter.base

import android.view.View
import com.sanaebadi.domain.model.DomainModel


class EmptyViewHolder(val view: View) : BaseViewHolder<DomainModel>(view) {

    override fun getType(): Int = 0

    override fun bind(data: DomainModel?) {}

}