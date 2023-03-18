package com.ucne.parcial2.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.parcial2.data.remote.TicketEntity.TicketEntity
import com.ucne.parcial2.data.remote.dao.TicketDao

@Database(
    entities = [
        TicketEntity::class
    ],
    version = 1
)
abstract class TicketDb: RoomDatabase() {
    abstract val ticketDao: TicketDao
}