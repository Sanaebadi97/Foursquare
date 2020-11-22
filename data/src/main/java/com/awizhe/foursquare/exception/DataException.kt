package com.awizhe.foursquare.exception

class DataException(val code: String, override val message: String) : Exception()
