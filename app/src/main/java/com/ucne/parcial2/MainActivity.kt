package com.ucne.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucne.parcial2.ui.theme.Parcial2Theme
import android.os.Build
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.parcial2.ui.theme.Ticket.TicktetListScreen
import com.ucne.parcial2.ui.theme.Tickets.TicketsScreen
import com.ucne.parcial2.ui.theme.navegacion.DrawerMenu
import com.ucne.parcial2.ui.theme.navegacion.ScreenModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenModule.Start.route
                    ) {
                        composable(ScreenModule.Start.route) {
                            DrawerMenu(navController = navController)
                        }
                        composable(ScreenModule.TicketsList.route) {
                            TicktetListScreen(onNewTicket = {}, navController = navController)
                        }
                        composable(ScreenModule.Tickets.route) {
                            TicketsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Parcial2Theme {
        Greeting("Android")
    }
}