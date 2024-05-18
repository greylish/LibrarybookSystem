package com.jayson.librarybookssystem.ui.theme.screens.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jayson.librarybookssystem.R

@Composable
fun GalleryScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Image(painter = painterResource(id = R.drawable.img  ),
            contentScale = ContentScale.FillBounds,
            contentDescription = "background",
        )
    }
}
@Preview
@Composable
fun GalleryScreenPreview(){
    GalleryScreen(rememberNavController())
}