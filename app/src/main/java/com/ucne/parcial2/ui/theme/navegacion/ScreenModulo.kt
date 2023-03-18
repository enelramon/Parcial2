package com.ucne.parcial2.ui.theme.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModule(val route: String, val title: String, val icon: ImageVector) {
    object Start : ScreenModule("ui","Inicio", Icons.TwoTone.Home)
    object Tickets : ScreenModule("tickets", "Registro Tickets", Icons.TwoTone.LocalActivity)
    object TicketsList : ScreenModule("tickets_list","Lista Tickets", Icons.TwoTone.ConfirmationNumber)
}