package com.jayson.librarybookssystem.ui.theme.screens.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.jayson.librarybookssystem.data.BookViewModel
import com.jayson.librarybookssystem.models.Upload
import com.jayson.librarybookssystem.models.book
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_UPDATE_BOOK

@Composable
fun ViewUploadScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var bookRepository = BookViewModel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var books = bookRepository.viewbooks(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All books",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Magenta)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(books){
                    Uploadbook(
                        subject = it.subject,
                        form = it.form,
                        bookNo = it.bookno,
                        navController = navController,
                        bookRepository = bookRepository)
                }
            }
        }
    }
}

fun Uploadbook(subject: String, form: String, bookNo: String, navController: NavHostController, bookRepository: BookViewModel) {

}

@Composable
fun Uploadbook(subject:String, form:String, bookNo:String,imageUrl:String,
               navController: NavHostController, bookRepository:BookViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = subject)
        Text(text = form)
        Text(text = bookNo)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Button(onClick = {
           bookRepository.deleteBook(bookNo, form, subject)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            //bookRepository.updatebook(subject,form,bookNo)
            navController.navigate(ROUTE_UPDATE_BOOK+"/bookNo")

        }) {
            Text(text = "Update")
        }
    }
}