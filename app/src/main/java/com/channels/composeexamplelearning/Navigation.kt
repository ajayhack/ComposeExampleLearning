package com.channels.composeexamplelearning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = Screen.DetailScreen.route + "/{name}" ,
        arguments = listOf(navArgument("name"){
            type = NavType.StringType
            defaultValue = "Ajay Thakur"
            nullable = true
        })
        ){ entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var name by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name, onValueChange = {
            name = it
        })

        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            navController.navigate(Screen.DetailScreen.route + "/$name")
        }) {
            Text(text = "Open DetailScreen")
        }
    }
}

@Composable
fun DetailScreen(name : String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name ?: "")
    }

}