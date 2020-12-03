package com.sanaebadi.foursquare.presentaion.ui.fragment.base

import android.content.Context
import android.os.Bundle
import com.sanaebadi.foursquare.presentaion.model.MessageModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), MessagePresenter {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showMessage(message: MessageModel) {
        if (message.message != null) {
            showMessage(message.message!!)
        } else if (message.resource != null) {
            showMessage(message.resource!!)
        }
    }

}