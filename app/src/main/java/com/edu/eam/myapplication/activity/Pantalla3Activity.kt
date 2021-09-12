package com.edu.eam.myapplication.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.edu.eam.myapplication.R
import com.edu.eam.myapplication.databinding.ActivityPantalla3Binding
import com.edu.eam.myapplication.model.Estudiante
import com.edu.eam.myapplication.utils.Constantes

class Pantalla3Activity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPantalla3Binding
    var estado: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val estudiante: Estudiante? = intent.getParcelableExtra("estudiante")
        if (estudiante != null) {
            Log.e("ACTIVIDAD3", estudiante.toString())
        }

        binding.imagen.setOnClickListener (this)

        binding.leerDatos.setOnClickListener(this)
    }

    override fun onBackPressed() {
        val intent = intent
        intent.putExtra("RESPUESTA", "Mensaje enviado desde la pantalla 3")
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.imagen.id -> {
                if(estado) {
                    estado = false
                    binding.imagen.setImageResource(R.drawable.img2)
                } else {
                    estado = true
                    binding.imagen.setImageResource(R.drawable.img1)
                }

            }

            binding.leerDatos.id -> {
                val nombre = binding.nombre.text
                val edad = binding.edad.text

                Constantes.mostrarMensaje(this, "${nombre} y ${edad}")
            }
        }
    }
}