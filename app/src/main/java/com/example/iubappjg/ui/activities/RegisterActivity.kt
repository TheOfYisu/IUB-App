package com.example.iubappjg.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.iubappjg.R
import com.example.iubappjg.ui.viewModels.UserViewModels
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {

    lateinit var inp_email:EditText
    lateinit var inp_password:EditText
    lateinit var inp_name:EditText
    lateinit var inp_phone:EditText
    lateinit var text_error_register:TextView
    private lateinit var userViewModel: UserViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userViewModel= ViewModelProvider(this).get(UserViewModels::class.java)

        inp_name=findViewById(R.id.inp_name_register)
        inp_email=findViewById(R.id.inp_email_register)
        inp_password=findViewById(R.id.inp_password_register)
        inp_phone=findViewById(R.id.inp_phome_register)
        text_error_register=findViewById(R.id.text_error_register)

        findViewById<Button>(R.id.btn_chm_register)
            .setOnClickListener {
                val name = inp_name.text.toString()
                val email = inp_email.text.toString()
                val password = inp_password.text.toString()
                val phone = inp_phone.text.toString()
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()){
                    text_error_register.text="Revise que no tega campos vacios."
                }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    text_error_register.text="Correo incorrecto, revice la cancion."
                }else if (phone.length<10 || phone.length>10){
                    text_error_register.text="Telefono incorrecto, tiene que tener 10 digitos."
                }else {
                    userViewModel.usersSaved(name, email, password, phone)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

            }

        findViewById<TextView>(R.id.text_login_register_2)
            .setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        findViewById<ImageButton>(R.id.btn_back_register)
            .setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


    }
}