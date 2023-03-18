package com.ucne.parcial2.ui.theme.Ticket

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.TicketEntity.TicketEntity
import com.ucne.parcial2.data.remote.repositorio.TicketRepositorio

data class TicketsUiState(
    val ticketsList: List<TicketEntity> = emptyList()
)

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepositorio
) : ViewModel() {

    var asunto by mutableStateOf("")
    var empresa by mutableStateOf("")
    var encargadoId by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")
    var fecha by mutableStateOf("")
    var orden by mutableStateOf("")

    var isValid: Boolean by mutableStateOf(false)

    var uiState = MutableStateFlow(TicketsUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            refrescarTicket()
        }
    }

    private suspend fun refrescarTicket() {
        ticketRepository.getList().collect { lista ->
            uiState.update {
                it.copy(ticketsList = lista)
            }
        }
    }

    fun insertar() {

        val ticket = TicketEntity(
            asunto = asunto,
            empresa = empresa,
            encargadoId = encargadoId.toIntOrNull() ?: 0,
            especificaciones = especificaciones,
            estatus = estatus,
            fecha = fecha,
            orden = orden.toIntOrNull() ?: 0
        )

        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.insert(ticket)
            Limpiar()
        }
    }

    private fun Limpiar() {
        asunto = ""
        empresa = ""
        encargadoId = ""
        especificaciones = ""
        estatus = ""
        fecha = ""
        orden = ""
    }
}