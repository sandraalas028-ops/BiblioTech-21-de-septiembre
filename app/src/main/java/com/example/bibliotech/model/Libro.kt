package com.example.bibliotech.model

/*
data class Libro (
    val id: Int,
    val titulo:String,
    val autor:String,
    val categoria: String,
    val descripcion:String,
    val anio: Int=0,
    val disponible: Boolean

    )*/

//convertir la clase en una tabla de Room
import androidx.room.Entity

//Define la llave primaria de la tabla
import androidx.room.PrimaryKey

//Ahora la clase representa a libros en SQlite y además sirve de modelo para la UI
@Entity("Libros")
data class libro(
    //Identificador unico generado automáticamente
    @PrimaryKey(autoGenerate=true)
    val id: Int=0,
    //Resto de propiedades
    val titulo:String,
    val autor:String,
    val categoria: String,
    val descripcion:String,
    val anio: Int=0,
    val disponible: Boolean
)