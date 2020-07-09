package com.s.ajrami.firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.s.ajrami.firestore.models.User
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlinx.android.synthetic.main.custom_item.*

class UpdateUser : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var id: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
          db = Firebase.firestore

            val x = intent.getSerializableExtra("data") as? User
           id = x!!.id
          update_firstname.setText(x.first)
          update_lastname.setText(x.last)
         update_born.setText(x.born)


            btn_save.setOnClickListener {
                val first = update_firstname.text.toString()
                val last = update_lastname.text.toString()
                val born = update_born.text.toString()


                when {
                    first.isEmpty() -> {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    }
                    last.isEmpty() -> {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    }
                    born.isEmpty() -> {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        updateUserById(id!!,first, last,born)

                        val i = Intent(this,ShowData::class.java)
                        startActivity(i)
                    }
                }

            }
        }


        private fun updateUserById(id: String,first: String, last: String,born:String ){
            var user = User(first, last,born).toMap()

            db!!.collection("user")
                .document(id)
                .update(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Log.e("sara","failed")

                }
        }
    }
