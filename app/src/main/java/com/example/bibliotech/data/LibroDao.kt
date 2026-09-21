package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotech.model.libro

@Dao
interface LibroDao {
    //función para insertar un libro CREATE
    @Insert
    fun insertarLibro(libro: libro):Long

    //función para traer libros READ
    @Query("SELECT * FROM libros")
    fun obtenerLibros(): List<libro>

    //Función para traer libros en base al ID READ
    @Query(" SELECT*FROM libros WHERE id=:id")
    fun obtenerLibroPorId(id:Int):libro?

    //Función para actualizar el libro
    @Update
    fun actualizarLibro(Libro:libro)
    //Función para eliminar un libro
    @Delete
    fun eliminarLibro(Libro:libro)
}
