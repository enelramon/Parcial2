package com.ucne.parcial2.data.remote.repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketsRepositoryImp @Inject constructor(
    private val api: TicketsApi
): TicketRepository {

    override fun getTickets(): Flow<Resource<List<TicketDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val ocupaciones =
                api.getTickets() //descarga las ocupaciones de internet, se supone quedemora algo

            emit(Resource.Success(ocupaciones)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override suspend fun putTicket(id: Int, ticketDto: TicketDto) {
        api.putTicket(id, ticketDto )
    }

    override suspend fun deleteTicket(id: Int) = api.deleteTicket(id)
}