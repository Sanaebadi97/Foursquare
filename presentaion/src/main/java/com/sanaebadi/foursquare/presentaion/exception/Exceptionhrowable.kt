package com.sanaebadi.foursquare.presentaion.exception

class Exceptionhrowable(val code: Int, message: String?) : Throwable(message) {

    constructor(code: Int) : this(code, null)

}