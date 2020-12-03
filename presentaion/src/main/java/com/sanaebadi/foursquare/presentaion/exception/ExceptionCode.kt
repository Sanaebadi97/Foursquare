package com.sanaebadi.foursquare.presentaion.exception

object ExceptionCode {
    const val ERROR_HAPPENED = 1000
    const val ERROR_TIMEOUT = 1001
    const val ERROR_IO = 1002
    const val ERROR_EMPTY_STATE = 1003

    const val ERROR_TIMEOUT_MESSAGE = "Server Time Out !"
    const val ERROR_IO_MESSAGE = "Error in connection!"
    const val ERROR_HAPPENED_MESSAGE = "Sorry, there was an error executing your request!"
}