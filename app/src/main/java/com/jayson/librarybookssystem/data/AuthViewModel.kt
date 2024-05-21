package com.jayson.librarybookssystem.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jayson.librarybookssystem.models.User
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_HOME
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_LOGIN
import com.jayson.librarybookssystem.navigation.Routes.ROUTE_REGISTER

class AuthViewModel(var navController: NavController, var context: Context){

    var mAuth:FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth= FirebaseAuth.getInstance()
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait.....")
    }
    fun signup(email:String,pass:String,confirmpass:String){
       progress.show()

        if (email.isBlank() || pass.isBlank() ||confirmpass.isBlank()){
            progress.dismiss()
            Toast.makeText(context,"Please email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }else if (pass != confirmpass){
            Toast.makeText(context,"Password do not match", Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                if (it.isSuccessful){

                    val userdata= User(email,pass,mAuth.currentUser!!.uid)
                    val regRef= FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_HOME)

                        }else{
                            Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_HOME)
                        }
                    }
                }else{
                    navController.navigate(ROUTE_REGISTER)
                }

            } }

    }
fun login(email: String,pass: String){
        progress.show()

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Successfully Logged in", Toast.LENGTH_LONG).show()
              navController.navigate(ROUTE_HOME)
               //navController.navigate(ROUTE_REGISTER)
            }
            else{
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

}
fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
}
fun isloggedin():Boolean{
        return mAuth.currentUser !=null
}

}