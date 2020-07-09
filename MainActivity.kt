package com.s.ajrami.firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.s.ajrami.firestore.models.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_item.view.*

class MainActivity : AppCompatActivity() {
    var db:FirebaseFirestore ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()

       btn_Add.setOnClickListener {
           val first= firstname.text.toString()
           val last = lastname.text.toString()
           var born = born.text.toString()

           if(first.isEmpty() || last.isEmpty()||born.isEmpty()){
               Toast.makeText(this,"Please enter Data", Toast.LENGTH_SHORT).show()

           }else{
               Toast.makeText(this, "test Add Successfully+ $first +$last", Toast.LENGTH_SHORT)
                   .show()

               addUser(first,last,born)
           }
       }

        btn_Get.setOnClickListener {

            val i = Intent(this, ShowData::class.java)
            startActivity(i)
        }}


    private fun addUser(first: String, last: String ,born:String) {

        var user =User(first,last,born)

        db!!.collection("users")
            .add(user)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                Log.d(
                    "sara", "DocumentSnapshot added with ID:  ${documentReference.id}"
                )

            })
            .addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                Log.w(
                    "sara",
                    "Error adding document",
                    e
                )
            })

    }


    }
