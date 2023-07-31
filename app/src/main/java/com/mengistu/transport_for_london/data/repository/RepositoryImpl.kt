package com.mengistu.transport_for_london.data.repository

import com.mengistu.transport_for_london.data.model.LineModel
import com.mengistu.transport_for_london.data.remote.TFLUAPI
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val tfluapi: TFLUAPI) : Repository {
    override suspend fun getLineStatus(): LineModel = tfluapi.getLineStatus()
}