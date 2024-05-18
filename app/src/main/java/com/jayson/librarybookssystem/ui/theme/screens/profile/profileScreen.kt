package com.jayson.librarybookssystem.ui.theme.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavController){
    Card (modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .aspectRatio(3f),
        colors = CardDefaults.cardColors(containerColor = Color.White)){
        Box (
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "name")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Go to home screen")
        }
    }
}
@Preview
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(rememberNavController())
}