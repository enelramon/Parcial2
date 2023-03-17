package com.ucne.parcial2.data.remote.dao


import kotlinx.coroutines.flow.Flow
import androidx.room.*
import com.ucne.parcial2.data.remote.TicketEntity.TicketEntity

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticketEntity: TicketEntity)

    @Delete
    suspend fun delete(ticketEntity: TicketEntity)

    @Query("""
        SELECT * 
        FROM Tickets
        WHERE TicketId=:ticketId
        LIMIT 1
    """)
    suspend fun find(ticketId: Int): TicketEntity?

    @Query("""SELECT * 
        FROM Tickets
        ORDER BY ticketId desc
    """)
    fun getList(): Flow<List<TicketEntity>>
    @Query("""SELECT * 
        FROM Tickets
        WHERE enviado=0 
    """)
    suspend fun getNoEnviadas(): List<TicketEntity>

}

class dao{
    fun save():Boolean{
        return true
    }
}