package com.example.bibliotech.data

import com.example.bibliotech.model.libro
class LibroRepository(
    private val libroDao: LibroDao
) {

    fun insertarLibro(libro: libro):Long {
       return libroDao.insertarLibro(libro)
    }

    fun obtenerLibros(): List<libro> {
        return libroDao.obtenerLibros()
    }

    fun obtenerLibroPorId(id:Int):libro?{
     return libroDao.obtenerLibroPorId(id)
    }

    fun actualizarLibro(Libro:libro){
        libroDao.actualizarLibro(Libro)
    }

    fun eliminarLibro(Libro:libro){
        libroDao.eliminarLibro(Libro)
    }
}
