package com.example.iubappjg.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iubappjg.R
import com.example.iubappjg.data.model.UserModel
import com.example.iubappjg.ui.viewModels.UserViewModels
import com.example.iubappjg.utils.Common

class LoginActivity : AppCompatActivity() {

    lateinit var inp_email: EditText
    lateinit var inp_password: EditText
    lateinit var btn_chm_login:Button
    lateinit var btn_back_login:ImageButton

    private lateinit var userViewModel: UserViewModels

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        onInit()

        userViewModel=ViewModelProvider(this).get(UserViewModels::class.java)

        validateSession()

        userViewModel.loginResult.observe(this) { isValidLogin ->
            if (isValidLogin) {
                val email = inp_email.text.toString()
                val password = inp_password.text.toString()
                saveSeccion(email, password)
                goProfile()
            } else {
                Common.showToast(this, "Credenciales incorrectas.")
            }
        }

        btn_chm_login.setOnClickListener {
            val email = inp_email.text.toString()
            val password = inp_password.text.toString()
            if(email.isNotEmpty() || password.isNotEmpty()){
                userViewModel.validateLogin(email, password)
            }else{
                Common.showToast(this, "Por favor ingrese sus credenciales.")
            }
        }

        findViewById<TextView>(R.id.text_reset_password)
            .setOnClickListener {
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            }

        findViewById<TextView>(R.id.text_register_login_2)
            .setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

        btn_back_login.setOnClickListener {
            finish()
        }
    }

    private fun validateSession(){
        val sp=getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val email=sp.getString("email", "")
        val password=sp.getString("password", "")

        if(email != null && password != null){
            if(email.isNotEmpty() && password.isNotEmpty()){
                goProfile()
            }
        }
    }

    private fun goProfile(){
        val intent = Intent(this, ActionsActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveSeccion(email: String, password:String){
        val sp=getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val edit=sp.edit()
        edit.putString("email", email)
        edit.putString("password", password)
        edit.apply()
    }

    private fun onInit(){
        inp_email=findViewById(R.id.inp_email_login)
        inp_password=findViewById(R.id.inp_password_login)
        btn_chm_login=findViewById(R.id.btn_chm_login)
        btn_back_login=findViewById(R.id.btn_back_login)
    }

}