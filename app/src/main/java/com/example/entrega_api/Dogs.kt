package com.example.entrega_api
data class Dogs(
    var fileSizeBytes : Int,
    var url: String) {

    override fun toString(): String {
        return " $fileSizeBytes , URL: $url \n"
    }
}

