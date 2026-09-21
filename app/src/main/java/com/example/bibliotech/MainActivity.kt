package com.example.bibliotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bibliotech.ui.Navegacion
import com.example.bibliotech.ui.theme.BiblioTechTheme

import androidx.lifecycle.lifecycleScope
import com.example.bibliotech.data.librosPrueba
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import com.example.bibliotech.data.librosPrueba

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val app = application as BibliotecaApplication
        val repository = app.libroRepository

        lifecycleScope.launch(Dispatchers.IO) {

            if (repository.obtenerLibros().isEmpty()) {

                librosPrueba.forEach { libro ->
                    repository.insertarLibro(libro)
                }
            }

            val librosGuardados = repository.obtenerLibros()

            println("LIBROS EN ROOM: ${librosGuardados.size}")

            librosGuardados.forEach {
                println("Libro: ${it.id} - ${it.titulo}")
            }


    }


        setContent {

            BiblioTechTheme {

                val navController = rememberNavController()

                Navegacion(
                    navController = navController
                )
            }
        }



    }
}
