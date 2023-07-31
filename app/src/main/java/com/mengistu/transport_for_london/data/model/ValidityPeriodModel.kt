package com.mengistu.transport_for_london.data.model


import com.google.gson.annotations.SerializedName

data class ValidityPeriodModel(
    @SerializedName("fromDate")
    val fromDate: String?,
    @SerializedName("isNow")
    val isNow: Boolean?,
    @SerializedName("toDate")
    val toDate: String?,
    @SerializedName("\$type")
    val type: String?
)