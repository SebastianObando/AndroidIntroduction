package com.edu.eam.myapplication.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.edu.eam.myapplication.R
import com.edu.eam.myapplication.model.Estudiante
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "ACTIVIDAD1"

    lateinit var resultLauncher:ActivityResultLauncher<Intent>

    lateinit var texto: TextView

    lateinit var valorTexto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texto = findViewById(R.id.texto_principal)

        valorTexto = if(savedInstanceState != null){
            savedInstanceState?.getString("texto", "Mensaje inicial")
        } else {
            "Mensaje Inicial"
        }
        texto.text = valorTexto
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onActivityResult(it.resultCode, it)
        }
    }

    fun irPantalla2(v: View) {
        Log.v(TAG, "Pasar a la pantalla 2")
        val intent = Intent(this, Pantalla2Activity::class.java)
        startActivity(intent)
    }

    fun irPantalla3(v: View) {
        Log.v(TAG, "Pasar a la pantalla 3")
        val intent = Intent(this, Pantalla3Activity::class.java)
        var estudiante1 = Estudiante(Date(), "Cuadrado", floatArrayOf(3.6F, 4.6F, 2.4F));

        val estudiante2 = Estudiante(Date(), "Juanita", floatArrayOf(3.6F, 4.6F, 3.4F));
        estudiante2.amigos.add(estudiante1)

        intent.putExtra("estudiante", estudiante2)

        resultLauncher.launch(intent)
    }

    fun cambiarTexto(v: View) {
        valorTexto = "Nuevo Texto"
        texto.text = valorTexto
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("texto", valorTexto)
        //outState.putParce

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.v(TAG, savedInstanceState.getString("texto").toString())
        texto.text = savedInstanceState.getString("texto")

    }

    private fun onActivityResult(resultCode: Int, result:ActivityResult) {
        val data = result.data?.extras
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Mensaje recibido: ${data?.getString("RESPUESTA")}", Toast.LENGTH_LONG).show()
        } else if (resultCode == Activity.RESULT_CANCELED) {

        }
    }
}