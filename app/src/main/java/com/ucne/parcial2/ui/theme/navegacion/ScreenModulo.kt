package com.ucne.parcial2.ui.theme.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModule(val route: String, val title: String, val icon: ImageVector) {
    object Start : ScreenModule("ui","Inicio", Icons.TwoTone.Home)
    object Tickets : ScreenModule("ticket", "Registro Ticket", Icons.TwoTone.LocalActivity)
    object TicketsList : ScreenModule("tickets_list","Consulta Tickers", Icons.TwoTone.ConfirmationNumber)
}