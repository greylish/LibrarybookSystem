package com.jayson.librarybookssystem.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jayson.librarybookssystem.R
import com.jayson.librarybookssystem.data.BookViewModel
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_ADD_BOOK
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_VIEW_BOOK

//import com.jayson.librarybookssystem.navigation.Routes.ROUTE_VIEW_BOOK

@Composable
fun HomeScreen(navController: NavController){
    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.img_1), contentDescription = "home",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
    }
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {


        Text(text = "Welcome to Library",
            color = Color.Cyan,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_ADD_BOOK)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add book")
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_VIEW_BOOK)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "View books")
        }


    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}