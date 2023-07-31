package com.mengistu.transport_for_london.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mengistu.transport_for_london.common.Resource
import javax.inject.Inject
import com.mengistu.transport_for_london.domain.model.usecase.getLineStatusUseCase.GetLineStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LineStatusViewModel @Inject constructor(val getLineStatusUseCase: GetLineStatusUseCase) : ViewModel() {

    init {
        getLineStatus()
    }

    private val _state = mutableStateOf(LineStatusState())
    val state : State<LineStatusState> = _state



    private fun getLineStatus(){
        getLineStatusUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = LineStatusState(line = result.data)
                }
                is Resource.Loading -> {
//                    _state.value = LineStatusState(line = emptyList(), isLoading = true, error= "")
                }
                is Resource.Error -> {
                   _state.value = LineStatusState(error = result.message ?: "Unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }



}