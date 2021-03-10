package com.sanaebadi.foursquare.presentaion.ui.fragment.base

import androidx.annotation.StringRes
import com.sanaebadi.foursquare.presentaion.model.MessageModel

interface MessagePresenter {

    fun showMessage(message: String)
    fun showMessage(@StringRes resourceId: Int)
    fun showMessage(message: MessageModel)
}