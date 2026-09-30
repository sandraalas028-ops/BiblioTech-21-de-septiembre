package com.example.bibliotech.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotech.model.Prestamo

@Dao
interface PrestamoDao {
    //Aqui se hace el crud y los métodos necesarios para trabajar con el ROOM

    @Insert
    fun insertar(prestamo: Prestamo)

    @Query("SELECT * FROM Prestamos WHERE devuelto=0")
    fun obtenerPrestamoActivos():List<Prestamo>
    @Query("SELECT*FROM Prestamos WHERE id= :id")
    fun obtenerPrestamoPorId(id: Int): Prestamo?
}