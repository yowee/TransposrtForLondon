package com.mengistu.transport_for_london.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mengistu.transport_for_london.data.model.LineModelItemModel
import com.mengistu.transport_for_london.data.model.LineStatuseModel

@Composable
fun LineStatusItem(lineStatus: LineStatuseModel?) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Line ID: ${lineStatus?.lineId}")
        Text(text = "Status Severity: ${lineStatus?.statusSeverity}")
        Text(text = "Status Severity Description: ${lineStatus?.statusSeverityDescription}")
        Text(text = "Reason: ${lineStatus?.reason}")
        Text(text = "Closure Text: ${lineStatus?.disruption?.closureText}")
    }

}

@Composable
fun LineStatusScreen(
    viewModel: LineStatusViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val expandedState = remember { mutableStateListOf<Boolean>() }
    expandedState.addAll(List(state.line?.size ?: 0) { false })

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(state.line ?: emptyList()) { index, item ->
            LineItem(
                item = item,
                expanded = expandedState[index]
            ) {
                expandedState[index] = !expandedState[index]
            }
        }
    }
}





@Composable
fun LineItem(
    item: LineModelItemModel,
    expanded: Boolean,
    onItemClicked: () -> Unit
) {
    val icon: ImageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClicked() },

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(        modifier = Modifier.weight(1f) // This will make Text take the available space
                    ,text = item.name.toString())

            Status(item.lineStatuses?.get(0)?.statusSeverityDescription.toString())

            Icon(imageVector = icon, contentDescription = "Expand/Collapse Status" )
        }

        if (expanded) {

            val reasonText = item.lineStatuses?.get(0)?.reason ?: ""
            val reasonVisible = rememberUpdatedState(expanded)

            AnimatedVisibility(
                visible = reasonVisible.value,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                
                LineStatusItem(lineStatus = item.lineStatuses?.get(0))
                
//                Text(
//                    text = reasonText,
//
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 8.dp)
//                        .animateContentSize() // Animate content size when it becomes visible or gone
//                )
            }
        }
    }
}

@Composable
fun Status(status: String) {
    Text(text = status)
}

@Preview
@Composable
fun PreviewNameAndAnimatedStatus() {
    MaterialTheme {

    }
}


/*


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mengistu.transport_for_london.data.model.LineModelItemModel
import com.mengistu.transport_for_london.data.model.LineStatuseModel

@Composable
fun LineStatusItem(lineStatus: LineStatuseModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Line ID: ${lineStatus.lineId}")
        Text(text = "Status Severity: ${lineStatus.statusSeverity}")
        Text(text = "Status Severity Description: ${lineStatus.statusSeverityDescription}")
        Text(text = "Reason: ${lineStatus.reason}")
        Text(text = "Closure Text: ${lineStatus.disruption?.closureText}")
    }
}

@Composable
fun LineStatusScreen(
    viewModel: LineStatusViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val expandedState = remember { mutableStateListOf<Boolean>() }
    expandedState.addAll(List(state.line?.size ?: 0) { false })

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(state.line ?: emptyList()) { index, item ->
                LineItem(
                    item = item,
                    expanded = expandedState[index]
                ) {
                    expandedState[index] = !expandedState[index]
                }
            }
        }

        // Add header content here, if needed
        // For example:
        // Text(text = "Header", style = MaterialTheme.typography.h4, modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun LineItem(
    item: LineModelItemModel,
    expanded: Boolean,
    onItemClicked: () -> Unit
) {
    val icon: ImageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClicked() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.name.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f) // This will make Text take the available space
            )

            Status(item.lineStatuses?.get(0)?.statusSeverityDescription.toString())

            Icon(imageVector = icon, contentDescription = null)
        }

        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                items(item.lineStatuses ?: emptyList()) { lineStatus ->
                    if (lineStatus != null) {
                        LineStatusItem(lineStatus)
                    }
                }
            }

            Text(
                text = item.lineStatuses?.get(0)?.reason ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun Status(status: String) {
    Text(text = status, style = MaterialTheme.typography.bodyMedium)
}

@Preview
@Composable
fun PreviewLineStatusScreen() {
    MaterialTheme {
        LineStatusScreen()
    }
}
*/

