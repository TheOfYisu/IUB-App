package com.example.iubappjg.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.iubappjg.R
import com.example.iubappjg.ui.adapters.UserAdapter
import com.example.iubappjg.ui.viewModels.UserViewModels
import com.example.iubappjg.utils.Common

class ActionsActivity : AppCompatActivity() {

    lateinit var get_email: String
    lateinit var get_password: String
    lateinit var get_name: String
    lateinit var get_phone: String
    lateinit var title_actions: TextView

    private lateinit var userViewModel: UserViewModels
    private lateinit var recyclerView: RecyclerView

    private val tag = "ActionsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actions)
        title_actions=findViewById(R.id.title_actions)

        userViewModel= ViewModelProvider(this).get(UserViewModels::class.java)
        recyclerView = findViewById(R.id.res_list_users)

        onInit()

        userViewModel.user.observe(this) { user ->
            if (user!=null) {
                get_email=user.email
                get_name=user.name
                get_password=user.password
                get_phone=user.phone
                title_actions.text= "Hola, $get_name"
            } else {
                Common.showToast(this, "Error al cargar datos del Usuario.")
            }
        }

        userViewModel.userList.observe(this){users->
            Log.d(tag, "Usuarios: $users")
            val adapter = UserAdapter(users)
            recyclerView.adapter = adapter
        }

        userViewModel.getUsers()

        findViewById<ImageButton>(R.id.btn_back_actions).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            closeSession()
            logout()
        }
    }

    override fun onResume() {
        super.onResume()
        onInit()
        userViewModel.getUsers()
    }

    private fun onInit(){
        val sp=getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val email=sp.getString("email", "")
        val password=sp.getString("password", "")

        if(email != null && password != null){
            if(email.isNotEmpty() && password.isNotEmpty()){
                userViewModel.getUser(email, password)
            }
        }
    }

    private fun logout(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun closeSession(){
        val sp=getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val edit=sp.edit()
        edit.remove("email")
        edit.remove("password")
        edit.apply()
    }
}