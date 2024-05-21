package com.jayson.librarybookssystem.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.jayson.librarybookssystem.models.Upload
import com.jayson.librarybookssystem.models.book
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_LOGIN
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_VIEW_UPLOAD

class BookViewModel(var navController: NavController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog


    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun savebook(subject: String, form: String, bookNo: String) {
        var id = System.currentTimeMillis().toString()
        var bookData = book(subject, form, bookNo)
        var bookRef = FirebaseDatabase.getInstance().getReference()
            .child("book/$id")
        progress.show()
        bookRef.setValue(bookData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewbooks(
        book: MutableState<Upload>,
        books: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        val ref = FirebaseDatabase.getInstance().getReference().child("books")
        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                books.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Upload::class.java)
                   book.value = value!!
                    books.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }

    fun deleteBook(bookNo: String, form: String, subject: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("$subject of class$form no $bookNo has been deleted")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "book deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateBook(bookNo: String, form: String, subject: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("subject:/$subject")
        progress.show()
        var updateData = book(subject, form, bookNo)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun savebookWithImage(
        subject: String,
        bookNo: String,
        form: String,
        filePath: Uri
    ) {
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                 //Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = Upload(
                        subject, form,
                        bookNo, imageUrl
                    )
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_UPLOAD)
                }
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

fun viewUploads(
        upload: MutableState<Upload>,
        uploads: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }
}

