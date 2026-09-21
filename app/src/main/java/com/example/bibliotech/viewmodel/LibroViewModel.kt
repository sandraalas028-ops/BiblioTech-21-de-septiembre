package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//LibroViewModel seré el encargado de solicitar los datos al Repository
class libroViewModel(application: Application): AndroidViewModel(application){

    private val repository=
        (application as BibliotecaApplication).libroRepository

    //CREAMOS UN ESTADO QUE ALMACENA LA LISTA DE LIBROS, INICIALMENTE VACÍA
    private val _libros=MutableStateFlow<List<libro>>(emptyList())

    val libros : StateFlow<List<libro>> = _libros.asStateFlow()

            //OBTENEMOS EL LIBRO SELECCIONADO
    private val _libroSeleccionado=MutableStateFlow<libro?>(null)
    val libroSeleccionado:StateFlow<libro?> =_libroSeleccionado.asStateFlow()

            //METODO QUE LLENA EL VIEWMODEL CONSULTANDO AL ROOM Y ACTUALIZA EL ESTADO
    //OSEA LLENA LA LISTA _libros

    fun cargarLibros(){
       viewModelScope.launch(Dispatchers.IO){

           _libros.value=repository.obtenerLibros()
       }

    }

    fun insertarLibro(libro:libro){

        viewModelScope.launch(Dispatchers.IO){
            repository.insertarLibro(libro)
            _libros.value=repository.obtenerLibros()
        }
    }
    fun cargarLibroPorId(id:Int){

        viewModelScope.launch(Dispatchers.IO){
            _libroSeleccionado.value=repository.obtenerLibroPorId(id)
        }

    }
    fun actualizarLibro(Libro:libro){
      viewModelScope.launch(Dispatchers.IO){
          repository.actualizarLibro(Libro)
          _libros.value=repository.obtenerLibros()
          _libroSeleccionado.value=repository.obtenerLibroPorId(Libro.id)
      }

    }
    //METODO PARA ELIMINAR UN LIBRO
    fun eliminarLibro(Libro:libro){

        viewModelScope.launch(Dispatchers.IO){
            repository.eliminarLibro(Libro)
            _libros.value=repository.obtenerLibros()
        }
    }
}
