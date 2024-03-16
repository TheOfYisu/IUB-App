package com.example.iubappjg.ui.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.iubappjg.data.model.UserModel
import com.example.iubappjg.R
import com.example.iubappjg.ui.activities.UpdateActivity

class UserAdapter (private val users:List<UserModel>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private val tag="UserAdapter"

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(user:UserModel) {
            itemView.findViewById<TextView>(R.id.name_user).text = user.name
        }
    }

    override fun getItemCount(): Int {
        Log.d(tag, "Usuarios: ${users.size}")
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user=users[position]
        holder.bind(user)
        holder.bind(user)

        holder.itemView.findViewById<Button>(R.id.btn_call_card)
            .setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:${user.phone}")
                it.context.startActivity(intent)
            }

        holder.itemView.findViewById<Button>(R.id.btn_email_card)
            .setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data= Uri.parse("mailto:${user.email}")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo")
                intent.putExtra (Intent. EXTRA_TEXT ,"Cuerpo del correo")
                it.context.startActivity(intent)
            }

        holder.itemView.findViewById<Button>(R.id.btn_msn_card)
            .setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data= Uri.parse("sms:${user.phone}")
                intent.putExtra("sms_body","Este mensaje es de comunicacion de IUB.")
                it.context.startActivity(intent)
            }

        holder.itemView.findViewById<Button>(R.id.btn_edit_card)
            .setOnClickListener {
                val intent = Intent(it.context, UpdateActivity::class.java)
                intent.putExtra("id_user", user.uid)
                intent.putExtra("name", user.name)
                intent.putExtra("email", user.email)
                intent.putExtra("password", user.password)
                intent.putExtra("phone", user.phone)
                it.context.startActivity(intent)

            }

    }


}