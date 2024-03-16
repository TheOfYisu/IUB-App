package com.example.iubappjg.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.iubappjg.R
import com.example.iubappjg.ui.viewModels.UserViewModels
import com.example.iubappjg.utils.Common

class UpdateActivity : AppCompatActivity() {

    lateinit var inp_email: EditText
    lateinit var inp_password: EditText
    lateinit var inp_name: EditText
    lateinit var inp_phone: EditText
    lateinit var text_error_update: TextView
    private lateinit var userViewModel: UserViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        userViewModel= ViewModelProvider(this).get(UserViewModels::class.java)

        inp_name=findViewById(R.id.inp_name_update)
        inp_email=findViewById(R.id.inp_email_update)
        inp_password=findViewById(R.id.inp_password_update)
        inp_phone=findViewById(R.id.inp_phome_update)
        text_error_update=findViewById(R.id.text_error_update)

        val id_user_data = intent.getLongExtra("id_user", 0L)

        intent.getStringExtra("name")?.let {
            inp_name.setText(it)
        }
        intent.getStringExtra("email")?.let {
            inp_email.setText(it)
        }
        intent.getStringExtra("phone")?.let {
            inp_phone.setText(it)
        }
        intent.getStringExtra("password")?.let {
            inp_password.setText(it)
        }

        findViewById<Button>(R.id.btn_chm_register_update)
            .setOnClickListener {
                val name = inp_name.text.toString()
                val email = inp_email.text.toString()
                val password = inp_password.text.toString()
                val phone = inp_phone.text.toString()
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()){
                    text_error_update.text="Revise que no tega campos vacios."
                }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    text_error_update.text="Correo incorrecto, revice el correo."
                }else if (phone.length<10 || phone.length>10){
                    text_error_update.text="Telefono incorrecto, tiene que tener 10 digitos."
                }else {
                    Log.d("userviewModel",id_user_data.toString())

                    userViewModel.updateUser(email,password,name,phone,id_user_data)
                    Common.showToast(this, "Usuario actualizado.")
                    finish()
                }
            }

        findViewById<ImageButton>(R.id.btn_back_update)
            .setOnClickListener {
                finish()
            }
    }
}