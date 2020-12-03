package com.sanaebadi.foursquare.presentaion.model

import androidx.annotation.StringRes

data class MessageModel(
    var message: String? = null,
    @StringRes
    var resource: Int? = null
)