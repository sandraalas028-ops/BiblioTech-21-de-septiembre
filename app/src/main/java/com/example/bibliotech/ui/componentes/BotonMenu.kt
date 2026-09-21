package com.example.bibliotech.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

@Composable
fun BotonMenu(texto: String,
              onClick:() -> Unit){
    Button(
        onClick=onClick,
        modifier=Modifier.fillMaxWidth(),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF69B4),
            contentColor = Color.Black
        )
    ){
        Text(texto)
    }

}