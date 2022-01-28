package com.manju1375.musicwiki.config

class Constants {
    companion object{
        const val ENDPOINT_BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    }
    annotation class HttpError {
        companion object {
            const val BAD_REQUEST_ERROR = 400
            const val FORBIDDEN_ERROR = 403
            const val UNAUTHORISED_ERROR = 401
            const val INTERNAL_SERVER_ERROR = 500
            const val PRECONDITION_FAILED_ERROR = 412
        }
    }
}