package com.s.ajrami.firestore.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.s.ajrami.firestore.R
import com.s.ajrami.firestore.ShowData
import com.s.ajrami.firestore.UpdateUser
import com.s.ajrami.firestore.models.User
import kotlinx.android.synthetic.main.custom_item.view.*

class UserAdapter (var activity: Activity, var data: ArrayList<User>) :
RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    var db: FirebaseFirestore? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgview=itemView.img
        val txtfirst = itemView.first_name
        val txtlast = itemView.last_name
        val txtborn = itemView.Born
        val update=itemView.update


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.custom_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtfirst.text = data[position].first
        holder.txtlast.text = data[position].last
        holder.txtborn.text = data[position].born



        holder.itemView.setOnClickListener {
            val i = Intent(activity, ShowData::class.java)
            i.putExtra("first", data[position].first)
            i.putExtra("last", data[position].last)
            i.putExtra("born", data[position].born)

            activity.startActivity(i)

        }
        holder.update.setOnClickListener {
            val i = Intent(activity, UpdateUser::class.java)
            i.putExtra("data", data[position])

            activity.startActivity(i)

        }


        holder.itemView.setOnLongClickListener {
            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle("Delete student")
            alertDialog.setMessage("Are you sure ?")
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Yes") { _, _ ->
                Log.e("hzm",data[position].id)
                deleteUserById(data[position].id)
                data.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, data.size)
                Toast.makeText(activity, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(activity, "No", Toast.LENGTH_SHORT).show()
            }

            alertDialog.setNeutralButton("Rate Me") { _, _ ->
                Toast.makeText(activity, "Rate Me", Toast.LENGTH_SHORT).show()
            }

            alertDialog.create().show()
            true
        }

    }

        private fun deleteUserById(id: String) {

            db = Firebase.firestore

            db!!.collection("users").document(id)
                .delete().addOnSuccessListener {

                    Toast.makeText(activity,"Delete successfully",Toast.LENGTH_LONG).show()

                }.addOnFailureListener {
                    Log.e("sara", "failed")
                }


    }
}
