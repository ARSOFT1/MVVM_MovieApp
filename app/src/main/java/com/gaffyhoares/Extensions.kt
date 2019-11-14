package com.gaffyhoares

import com.gaffyhoares.data.network.response.BaseResponse


inline fun <reified T : BaseResponse> safeApi(block: () -> T): T = try {
    block()
} catch (e: Exception) {
    T::class.java.newInstance().apply {
    }
}


