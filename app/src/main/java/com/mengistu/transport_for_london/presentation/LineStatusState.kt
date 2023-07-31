package com.mengistu.transport_for_london.presentation

import com.mengistu.transport_for_london.data.model.LineModel
import com.mengistu.transport_for_london.data.model.LineModelItemModel

data class LineStatusState(
    val line: List<LineModelItemModel>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
