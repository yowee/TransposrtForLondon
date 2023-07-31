package com.mengistu.transport_for_london.data.repository

import com.mengistu.transport_for_london.data.model.LineModel

interface Repository {
    suspend fun getLineStatus() : LineModel
}
