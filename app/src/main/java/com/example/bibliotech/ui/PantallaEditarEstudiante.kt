package com.example.bibliotech.ui

import android.R.attr.text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bibliotech.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarEstudiante(
    estudiante:Estudiante,
    onGuardar:(Estudiante)->Unit,
    onCancelar:()->Unit
){
    //VARIABLES DEL FORMATO
    var carnet by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    //GRADO
    val grados = listOf(
        "1° Bachillerato",
        "2° Bachillerato",
        "3° Bachillerato"
    )
    var grado by remember { mutableStateOf(grados[0]) }
    var expandirGrado by remember { mutableStateOf(false) }

    //------SECCIÓN--------
    val secciones = listOf("A", "B", "C")
    var seccion by remember { mutableStateOf(secciones[0]) }
    var expandirSeccion by remember { mutableStateOf(false) }

    //-------ESTADO----------
    var activo by remember { mutableStateOf(true) }

    Scaffold(containerColor=Color.Black,
        topBar={
            TopAppBar(
                title={
                    Text("Editar Estudiante", color=Color.White)
                },
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor=Color.Black
                )
            )
        }){ padding ->
        Column(modifier= Modifier.padding(padding)){}

    }


}


