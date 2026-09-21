package com.example.bibliotech.ui

import android.R.attr.textStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.bibliotech.viewmodel.libroViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.bibliotech.model.libro
//IMPORTACIONES AGREGADAS
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


@Composable
fun PantallaAgregarLibro(
    viewModel: libroViewModel,
    onGuardar:()->Unit,
    onCancelar:()->Unit
){

  var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }

@OptIn(ExperimentalMaterial3Api::class)
Scaffold(containerColor=Color.Black,

    topBar={
        TopAppBar(
            title={
                Text(
                    "Agregar Libro",
                    color=Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor=Color.Black
            )

        )
    }
){paddingValues ->
    Column(modifier=Modifier.fillMaxSize().padding(20.dp).background(Color.Black).verticalScroll(rememberScrollState())
        .padding(paddingValues)){

        Spacer(modifier=Modifier.height(16.dp))

        OutlinedTextField(
            value=titulo,
            onValueChange={titulo=it},
            label={Text("Titulo")},
            modifier=Modifier.fillMaxWidth(),
                   //modificamos a los inputs
            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor=Color(0xFF38B2F6),
                unfocusedBorderColor = Color.White,
                focusedLabelColor=Color(0xFF60A5FA),
                unfocusedLabelColor=Color.LightGray,
                cursorColor=Color.White,
                focusedTextColor=Color.White,
                unfocusedTextColor=Color.White
            )
        )
      Spacer(modifier=Modifier.height(12.dp))

        Spacer(modifier=Modifier.height(16.dp))

        OutlinedTextField(
            value=autor,
            onValueChange={autor=it},
            label={Text("Autor")},
            modifier=Modifier.fillMaxWidth(),

            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor=Color(0xFF38B2F6),
                unfocusedBorderColor = Color.White,
                focusedLabelColor=Color(0xFF60A5FA),
                unfocusedLabelColor=Color.LightGray,
                cursorColor=Color.White,
                focusedTextColor=Color.White,
                unfocusedTextColor=Color.White
            )

        )
        Spacer(modifier=Modifier.height(12.dp))


        OutlinedTextField(
            value=categoria,
            onValueChange={categoria=it},
            label={Text("Categoría",)},
            modifier=Modifier.fillMaxWidth(),
            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor=Color(0xFF38B2F6),
                unfocusedBorderColor = Color.White,
                focusedLabelColor=Color(0xFF60A5FA),
                unfocusedLabelColor=Color.LightGray,
                cursorColor=Color.White,
                focusedTextColor=Color.White,
                unfocusedTextColor=Color.White
        )
        )
        Spacer(modifier=Modifier.height(12.dp))

        OutlinedTextField(
            value=descripcion,
            onValueChange={descripcion=it},
            label={Text("Descripción")},
            modifier=Modifier.fillMaxWidth(),
            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor=Color(0xFF38B2F6),
                unfocusedBorderColor = Color.White,
                focusedLabelColor=Color(0xFF60A5FA),
                unfocusedLabelColor=Color.LightGray,
                cursorColor=Color.White,
                focusedTextColor=Color.White,
                unfocusedTextColor=Color.White
            )
        )
        Spacer(modifier=Modifier.height(12.dp))

        OutlinedTextField(
            value=anio,
            onValueChange={anio=it},
            label={Text("Año")},
            modifier=Modifier.fillMaxWidth(),
            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor=Color(0xFF38B2F6),
                unfocusedBorderColor = Color.White,
                focusedLabelColor=Color(0xFF60A5FA),
                unfocusedLabelColor=Color.LightGray,
                cursorColor=Color.White,
                focusedTextColor=Color.White,
                unfocusedTextColor=Color.White
            )
        )
        Spacer(modifier=Modifier.height(12.dp))


        Button(
            onClick={
        val nuevoLibro=libro(
            titulo = titulo,
            autor = autor,
            categoria = categoria,
            descripcion = descripcion,
            anio = anio.toIntOrNull()?: 0,
            disponible = true)

            viewModel.insertarLibro(nuevoLibro)
            onGuardar()
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF69B4) // <- pon aquí el código exacto de tu rosa
            ),
            modifier=Modifier.fillMaxWidth()
            ){
            Text("Guardar libro")
        }

        Spacer(modifier=Modifier.height(8.dp))

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text="Cancelar",
                color=Color.White
            )

        }
    }
}

}