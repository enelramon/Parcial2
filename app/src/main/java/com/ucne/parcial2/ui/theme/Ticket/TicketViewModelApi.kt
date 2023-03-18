package com.ucne.parcial2.ui.theme.Ticket

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.remote.repositorio.TicketRepositorioApi
import com.ucne.parcial2.util.Resource

data class TicketsListState(
    val isLoading: Boolean = false,
    val tickets: List<TicketDto> = emptyList(),
    val error: String = ""
)

@HiltViewModel
class TicketApiViewModel @Inject constructor(
    private val ticketApiRepository: TicketRepositorioApi
) : ViewModel() {

    var uiState = MutableStateFlow(TicketsListState())
        private set

    init {
        ticketApiRepository.getTickets().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(tickets = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    uiState.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)

    }
}