package com.jayson.librarybookssystem.navigation.Routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jayson.librarybookssystem.ui.theme.screens.About.AboutScreen
import com.jayson.librarybookssystem.ui.theme.screens.book.AddbookScreen
import com.jayson.librarybookssystem.ui.theme.screens.book.ViewUploadScreen
import com.jayson.librarybookssystem.ui.theme.screens.gallery.GalleryScreen
import com.jayson.librarybookssystem.ui.theme.screens.home.HomeScreen
import com.jayson.librarybookssystem.ui.theme.screens.login.LoginScreen
import com.jayson.librarybookssystem.ui.theme.screens.profile.ProfileScreen
import com.jayson.librarybookssystem.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(modifier: Modifier=Modifier,
               navController: NavHostController=rememberNavController(),
               startDestination:String = ROUTE_REGISTER) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
        composable(ROUTE_PROFILE) { ProfileScreen(navController)}
        composable(ROUTE_LOGIN){ LoginScreen(navController)}
        composable(ROUTE_ABOUT){ AboutScreen(navController)}
        composable(ROUTE_ADD_BOOK){ AddbookScreen(navController)}
        composable(ROUTE_VIEW_UPLOAD){ ViewUploadScreen(navController) }
        composable(ROUTE_VIEW_BOOK){}
        //composable(ROUTE_UPDATE_BOOK){}
        composable(ROUTE_GALLERY){ GalleryScreen(navController)}
    }
}