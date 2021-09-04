package com.edu.eam.myapplication.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.edu.eam.myapplication.R
import com.edu.eam.myapplication.model.Estudiante

class Pantalla3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla3)

        val estudiante: Estudiante? = intent.getParcelableExtra("estudiante")
        if (estudiante != null) {
            Log.e("ACTIVIDAD3", estudiante.toString())
        }
    }

    override fun onBackPressed() {
        val intent = intent
        intent.putExtra("RESPUESTA", "Mensaje enviado desde la pantalla 3")
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}