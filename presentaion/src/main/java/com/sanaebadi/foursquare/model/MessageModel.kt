package com.sanaebadi.foursquare.model

import androidx.annotation.StringRes

data class MessageModel(
    var message: String? = null,
    @StringRes
    var resource: Int? = null
)