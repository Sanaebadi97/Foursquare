package com.sanaebadi.foursquare.exception

class Exceptionhrowable(val code: Int, message: String?) : Throwable(message) {

    constructor(code: Int) : this(code, null)

}