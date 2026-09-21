package com.example.bibliotech.ui

import android.R.attr.top
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.data.librosPrueba
import com.example.bibliotech.model.libro
import com.example.bibliotech.ui.componentes.TarjetaLibro
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bibliotech.BibliotecaApplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.bibliotech.viewmodel.libroViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PantallaCatalogo(
    onRegresar:()->Unit,
    onVerDetalles:(Int)->Unit,
    onAgregarLibro:()->Unit,
    mensaje:String?,
    onMensajeMostrado:()->Unit
) {

    //Se encarga que la interfaz esté pendiente del estado de la lista nueva
    val app = LocalContext.current.applicationContext as BibliotecaApplication
    val viewModel : libroViewModel =viewModel(

        factory=object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T{
                return libroViewModel(app as Application) as T
            }
        }
    )

    val libros by viewModel.libros.collectAsState()
    //mensaje mostrar cuando se elimine o actualice
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope=rememberCoroutineScope()

    LaunchedEffect( Unit){
        viewModel.cargarLibros()
    }

    LaunchedEffect(mensaje){
        if(mensaje!=null){
            snackbarHostState.showSnackbar(mensaje)
            onMensajeMostrado()

        }
    }

    //Capturar el texto escrito por el usuario
    var textoBusqueda by remember{ mutableStateOf("")}
    //lista de categorías
    val categoria=listOf("Todas","Literatura","Novela","Programacion")
    //guardar una por defecto y/o la que seleccione
    var categoriaSeleccionada by remember{mutableStateOf("Todas")}

    //Crear una nueva lista que compare los libros que coincidan con la búsqueda
    val librosFiltrados=libros.filter{ Libro ->
        val coincideTexto=Libro.titulo.contains( textoBusqueda,
            ignoreCase=true) || Libro.autor.contains(textoBusqueda,
                ignoreCase=true)
        val coincideCategoria=categoriaSeleccionada=="Todas"||
                Libro.categoria==categoriaSeleccionada
        coincideTexto && coincideCategoria

    }

    Scaffold(
        snackbarHost={SnackbarHost(hostState=snackbarHostState)},
        //añadiremos el boton que enlazara a la pantalla de crear un nuevo libro
        floatingActionButtonPosition = FabPosition.Start, //alineamos a la derecha

        floatingActionButton = {
            FloatingActionButton(onClick=onAgregarLibro,containerColor=Color.Magenta){
                Text("+",color=Color.Black)
            }
        },

        topBar= {
        TopAppBar(title = { Text("Catálogo de Libros") })
    }){padding  ->
        Column(modifier = Modifier.padding(padding)
            .padding(5.dp)
            .fillMaxSize()
        ) {

            //------------añadido------------------------
            OutlinedTextField(value=textoBusqueda,
                onValueChange={textoBusqueda=it},
                label={Text("Buscar libro o autor")},
                modifier = Modifier.fillMaxWidth(),
                textStyle= TextStyle(color = Color.White)
            )
            //Creación de los chips de categoría
            Spacer(modifier=Modifier.height(12.dp))
            Row(modifier=Modifier.horizontalScroll(state= rememberScrollState())){
                categoria.forEach{categoria->
                    FilterChip(selected=categoriaSeleccionada==categoria,
                        onClick={categoriaSeleccionada = categoria},
                        label={ Text(categoria)},

                        )

                    Spacer(modifier=Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            if(librosFiltrados.isEmpty()){
                //MODIFICACIÓN
                Column(modifier=Modifier.fillMaxWidth()
                    .padding(top=40.dp),
                    horizontalAlignment=Alignment.CenterHorizontally){
                    Icon(imageVector= Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription="Sin resultados",
                        modifier=Modifier.size(48.dp))

                    Spacer(modifier=Modifier.height(12.dp))
                    Text(text="No se encontraron libros",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text="Prueba con otro titulo o autor")
                }

            }else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(librosFiltrados) { Libro ->
                        TarjetaLibro(
                            Libro = Libro,
                            //Pasaremos el id del libro
                            onVerDetalles = { onVerDetalles(Libro.id) })
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))


        }

    }
    }

