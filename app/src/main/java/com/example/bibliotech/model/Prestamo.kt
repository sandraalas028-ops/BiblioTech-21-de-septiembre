package com.example.bibliotech.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    tableName="Prestamos",
    foreignKeys = [
        ForeignKey(
            entity = libro::class,
            parentColumns = ["id"],
            childColumns = ["idLibro"],

        ),
        ForeignKey(
            entity = Estudiante::class,
            parentColumns = ["id"],
            childColumns = ["idEstudiante"],

            ),
   ]
)


data class Prestamo (
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    //libro prestado
    val idLibro: Int,
    //estudiante que lo presta
    val idEstudiante: Int,
    //fecha en que se presta
    val fechaPrestamo: String,
    //fecha en que se debe devolver
    val fechaDevolucion: String,
    //Estado del préstamo
    val devuelto: Boolean = false
){
}