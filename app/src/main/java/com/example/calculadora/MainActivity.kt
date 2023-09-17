package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidwindowinsets.rememberImeState
import com.example.calculadora.ui.theme.AppTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContainer()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainContainer(){

    Scaffold(
        topBar = { Toolbar()}
    ){values->
        ContentView(values)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(){
    TopAppBar(
        title = { Text(
            "Sumadora V1",
            color = MaterialTheme.colorScheme.primary
            )},
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Is the menu app" )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentView(innerPadding: PaddingValues){
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value ){
        if(imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "Sumadora V1.2",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            "${result}",
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),

        ){
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally ,
                verticalArrangement = Arrangement.Center,
            ){
                OutlinedTextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    label = { Text("Numero 1") },
                    modifier = Modifier.width(240.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    label = { Text("Numero 2") },
                    modifier = Modifier.width(240.dp)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Button(onClick = {result = sumatoria(num1,num2) }) {
                    Text(text = "Sumar ")
                }
                Button(onClick = {result = resta(num1,num2) }) {
                    Text(text = "Restar")
                }
                Button(onClick = {result = multiplicacion(num1,num2) }) {
                    Text(text = "Multiplicar")
                }
            }

        }

    }

}

fun sumatoria(x: String,y:String): Double {
    var result = 0.0
    try {
       result = (x.toDouble() + y.toDouble())
    } catch (e: NumberFormatException) {
        result = 0.0
    }
    return result
}

fun multiplicacion(x: String,y:String): Double {
    var result = 0.0
    try {
        result = (x.toDouble() * y.toDouble())
    } catch (e: NumberFormatException) {
        result = 0.0
    }
    return result
}

fun resta(x: String,y:String): Double {
    var result = 0.0
    try {
        result = (x.toDouble() - y.toDouble())
    } catch (e: NumberFormatException) {
        result = 0.0
    }
    return result
}
