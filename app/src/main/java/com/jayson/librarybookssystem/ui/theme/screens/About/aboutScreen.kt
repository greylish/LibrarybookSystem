package com.jayson.librarybookssystem.ui.theme.screens.About

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_HOME
import com.jayson.librarybookssystem.ui.theme.screens.login.LoginScreen

@Composable
fun AboutScreen(navController: NavController){
    Column (horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ABOUT SCREEN")
        }
        Button(onClick = {navController.navigate(ROUTE_HOME)},
            colors = ButtonDefaults.buttonColors(Color.Red)) {
            Text(text = "To HOME Screen")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    LoginScreen(rememberNavController())
}