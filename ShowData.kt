package com.s.ajrami.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.s.ajrami.firestore.adapters.UserAdapter
import com.s.ajrami.firestore.models.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_data.*

class ShowData : AppCompatActivity() {

    var data = ArrayList<User>()
    var TAG = "sara"
    var db: FirebaseFirestore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        db = Firebase.firestore

        rvUser.layoutManager= LinearLayoutManager(this)
        val adapter=UserAdapter(this,data)
        rvUser.adapter=adapter
        getAllUsers()

    }

    fun getAllUsers(){
        db!!.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val user=document.toObject(User::class.java)
                        user.id=document.id
                        data.add(user)
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }

                    val adapter=UserAdapter(this,data)
                    rvUser.adapter=adapter
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }

    }

