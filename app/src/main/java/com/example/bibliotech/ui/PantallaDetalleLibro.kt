package com.example.bibliotech.ui

import android.R.attr.title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bibliotech.model.libro
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun PantallaDetalleLibro(
    Libro:libro,
    onRegresar:()->Unit,
    //añadiremos los parámetros para los eventos de los botones editar y eliminar
    onEditar:(Int)->Unit,
    onEliminar:(libro)->Unit,
    navController: NavController,
) {

    val snackbarHostState=remember{ SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val mensaje=
        backStackEntry
            ?.savedStateHandle
            ?.get<String>("mensaje")

    LaunchedEffect(mensaje) {
        if(mensaje!=null){
            snackbarHostState.showSnackbar(mensaje)
            backStackEntry
                ?.savedStateHandle
                ?.remove<String>("mensaje")
        }
    }

    var mostrarDialog by remember { mutableStateOf(false)}

    @OptIn(ExperimentalMaterial3Api::class)

    Scaffold(
        containerColor = Color.Black,
        snackbarHost = { SnackbarHost(hostState=snackbarHostState) },
        topBar = {

            TopAppBar(
                title = {
                    Text("Detalles del libro",
                        color = Color.White
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }) {paddingValues ->





Column(modifier=Modifier.fillMaxSize().padding(20.dp).padding(paddingValues)){
        Icon(
            imageVector= Icons.AutoMirrored.Filled.MenuBook,
            contentDescription="Libro",
            modifier=Modifier.height(30.dp),
            tint=Color.White
        )
    Spacer(modifier=Modifier.height(16.dp))

    Text(
            text=Libro.titulo,
            fontSize=28.sp,
            fontWeight= FontWeight.Bold,
        color=Color.White

        )
       Spacer(modifier=Modifier.height(16.dp))
        Text(
            text="Autor: ${Libro.autor}",
            fontSize=18.sp,
            color=Color.White
        )
        Text(
            text="Categoría: ${Libro.categoria}",
            fontSize=18.sp,
            color=Color.White
        )

    Text(
        text="Descripcion: ${Libro.descripcion}",
        fontSize=18.sp,
        color=Color.White
    )
        Text(
            text="Año publicación:  ${Libro.anio}",
            fontSize=18.sp,
            color=Color.White
        )

        Text(
            text="Disponibilidad: ${Libro.disponible}",
            fontSize=18.sp,
            color=Color.White
        )
        Spacer(modifier=Modifier.height(24.dp))

    Row(modifier=Modifier.fillMaxWidth(),
        horizontalArrangement= Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick={
            onEditar(Libro.id)
        },
            modifier=Modifier.weight(1f))
        {
            Text("Editar")

        }

        Button(onClick={
            //onEliminar(Libro.id)
            mostrarDialog=true
        },
            modifier=Modifier.weight(1f))
        {
            Text("Eliminar")

        }

    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedButton(
        onClick=onRegresar,
        modifier=Modifier.fillMaxWidth()

    ){
        Text("Regresar")
    }

    //Construiremos la ventana emergente cuando presionemos el boton eliminar

    if(mostrarDialog){
      AlertDialog(
          onDismissRequest={mostrarDialog=false},
          title={
              Text("Confirmacion")
          },
          text={
              Text("Estas seguro de eliminar \"${Libro.titulo}\"?")
          },
          confirmButton={
              Button(onClick={
                  mostrarDialog=false
                  onEliminar(Libro)

              }){
                  Text("Eliminar")
              }
          },
          dismissButton={
              Button(onClick={
                  mostrarDialog=false
              }){
                  Text("Cancelar")
              }
          }
      )
    }
}
 }
  }