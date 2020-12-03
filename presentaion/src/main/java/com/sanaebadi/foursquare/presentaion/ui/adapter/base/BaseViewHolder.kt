package com.sanaebadi.foursquare.presentaion.ui.adapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

     val mSubject = PublishSubject.create<BaseAction>()

    abstract fun bind(data: T?)

    abstract fun getType(): Int

    fun observe(): Observable<BaseAction> {
        return mSubject.hide()
    }

}