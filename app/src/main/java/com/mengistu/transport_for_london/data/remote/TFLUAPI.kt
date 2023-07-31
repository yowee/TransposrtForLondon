package com.mengistu.transport_for_london.data.remote

import com.mengistu.transport_for_london.common.Constants
import com.mengistu.transport_for_london.data.model.LineModel
import retrofit2.http.GET

interface TFLUAPI {
    @GET(Constants.STATUS_END_POINT)
    suspend fun getLineStatus() : LineModel
}