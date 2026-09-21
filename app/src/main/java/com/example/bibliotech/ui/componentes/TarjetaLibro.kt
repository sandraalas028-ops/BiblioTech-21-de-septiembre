package com.example.bibliotech.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.model.libro
import androidx.compose.material3.Button

import androidx.compose.material3.Icon
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

//este es el componente reutilizable que estamos utilizando
//para mostrar la información de cada libro
//dentro de cada card (tarjeta)
//recibe:
/*libro: contiene toda la información a mostrar
onVerDetalles: accion que se ejecutará cuando demos clic al botón "Ver detalles"

 */
@Composable
fun TarjetaLibro(
    Libro: libro, onVerDetalles:()->Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = "Libro",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = Libro.titulo,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Autor: ${Libro.autor}")

                }
            }

        }


                Button(
                    onClick = onVerDetalles,
                    modifier = Modifier.align(Alignment.End).padding(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF69B4)
                    )
                ) { Text("Ver") }
            }


        }





