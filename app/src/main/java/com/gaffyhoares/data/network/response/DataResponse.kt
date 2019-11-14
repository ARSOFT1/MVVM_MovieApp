package com.gaffyhoares.data.network.response


class DataResponse<T> : BaseResponse() {
    val data: T? = null
}


open class BaseResponse{
    val page: Int = 0
    val results: List<MoviesResult>? = null
    val totalPages: Int = 0
    val totalResults: Int = 0
}

