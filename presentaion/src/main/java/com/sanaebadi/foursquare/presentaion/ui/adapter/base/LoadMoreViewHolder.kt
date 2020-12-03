package com.sanaebadi.foursquare.presentaion.ui.adapter.base

import android.view.View

class LoadMoreViewHolder(val view: View) : BaseViewHolder<Unit>(view) {

    override fun getType(): Int = ViewTypeHolder.LOAD_MORE_VIEW

    override fun bind(data: Unit?) {

    }

}