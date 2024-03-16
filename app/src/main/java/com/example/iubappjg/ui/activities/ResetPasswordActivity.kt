package com.example.iubappjg.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.iubappjg.R

class ResetPasswordActivity : AppCompatActivity() {

    lateinit var inp_email:EditText
    lateinit var text_error_reset: TextView
    lateinit var check_send_email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        inp_email=findViewById(R.id.inp_email_reset_password)
        text_error_reset=findViewById(R.id.text_error_reset)
        check_send_email=findViewById(R.id.check_send_email)


        findViewById<Button>(R.id.btn_reset_password)
            .setOnClickListener {
                val email = inp_email.text.toString()

                if(email=="jesudgm.11@gmail.com"){
                    text_error_reset.text=""
                    check_send_email.text="Codigo enviado."
                }else{
                    text_error_reset.text="Correo no encontrado"
                    check_send_email.text=""
                }
            }

        findViewById<ImageButton>(R.id.btn_back_reset_password)
            .setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

    }
}