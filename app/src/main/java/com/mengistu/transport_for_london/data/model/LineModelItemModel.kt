package com.mengistu.transport_for_london.data.model


import com.google.gson.annotations.SerializedName

data class LineModelItemModel(
    @SerializedName("created")
    val created: String?,
    @SerializedName("crowding")
    val crowding: CrowdingModel?,
    @SerializedName("disruptions")
    val disruptions: List<Any?>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lineStatuses")
    val lineStatuses: List<LineStatuseModel?>?,
    @SerializedName("modeName")
    val modeName: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("routeSections")
    val routeSections: List<Any?>?,
    @SerializedName("serviceTypes")
    val serviceTypes: List<ServiceTypeModel?>?,
    @SerializedName("\$type")
    val type: String?
)