package com.mengistu.transport_for_london.data.model


import com.google.gson.annotations.SerializedName

data class DisruptionModel(
    @SerializedName("additionalInfo")
    val additionalInfo: String?,
    @SerializedName("affectedRoutes")
    val affectedRoutes: List<Any?>?,
    @SerializedName("affectedStops")
    val affectedStops: List<Any?>?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("categoryDescription")
    val categoryDescription: String?,
    @SerializedName("closureText")
    val closureText: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("\$type")
    val type: String?
)