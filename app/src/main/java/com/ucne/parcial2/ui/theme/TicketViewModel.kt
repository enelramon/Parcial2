//package com.ucne.parcial2.ui.theme
//
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.*
//import javax.inject.Inject
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.ucne.parcial2.data.remote.dto.TicketDto
//import com.ucne.parcial2.data.remote.repository.TicketRepository
//import com.ucne.parcial2.util.Resource
//
//data class TicketsListState(
//    val isLoading: Boolean = false,
//    val tickets: List<TicketDto> = emptyList(),
//    val error: String = ""
//)
//
//@HiltViewModel
//class TicketViewModel @Inject constructor(
//    private val ticketRepository: TicketRepository
//) : ViewModel() {
//
//    var uiState = MutableStateFlow(TicketsListState())
//        private set
//
//    init {
//        ticketRepository.getTickets().onEach { result ->
//            when (result) {
//                is Resource.Loading -> {
//                    uiState.update {
//                        it.copy(isLoading = true)
//                    }
//                }
//
//                is Resource.Success -> {
//                    uiState.update {
//                        it.copy(tickets = result.data ?: emptyList())
//                    }
//                }
//
//                is Resource.Error -> {
//                    uiState.update {
//                        it.copy(error = result.message ?: "Error desconocido")
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//
//}