package com.example.bibliotech.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.bibliotech.model.libro
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PantallaEditarLibro(
    Libro: libro,
    onGuardar: (libro) ->Unit,
    onCancelar:()->Unit

){
  var titulo by remember {mutableStateOf(Libro.titulo)}
    var autor by remember {mutableStateOf(Libro.autor)}
    var categoria by remember {mutableStateOf(Libro.categoria)}
    var descripcion by remember {mutableStateOf(Libro.descripcion)}
    var anio by remember {mutableStateOf(Libro.anio.toString())}

Scaffold(
    containerColor=Color.Black,
    topBar = {
         TopAppBar(
             title = {
                 Text("Editar libro", color = Color.White)
             },
             colors = TopAppBarDefaults.topAppBarColors(
                 containerColor = Color.Black
             )
         )

    }
) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(paddingValues).padding(16.dp)
    ) {
        Text("Editar libro", color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("autor") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("categoria") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("descripción") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                val libroEditado = Libro.copy(
                    titulo = titulo,
                    autor = autor,
                    categoria = categoria,
                    anio = anio.toIntOrNull() ?: Libro.anio,
                    descripcion = descripcion
                )
                onGuardar(libroEditado)

            },
            modifier = Modifier.fillMaxWidth()

        ) { Text("Guardar cambios") }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Cancelar") }

    }

}
}