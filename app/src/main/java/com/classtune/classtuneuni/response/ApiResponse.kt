package com.classtune.classtuneuni.response

object ApiResponse {
    data class Result(val status: Status)
    data class Status(val code: String, val msg: String)
//    data class Status(val msg: String)
}