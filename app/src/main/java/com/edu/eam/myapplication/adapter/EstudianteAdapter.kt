package com.edu.eam.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.eam.myapplication.R
import com.edu.eam.myapplication.activity.DetalleActivity
import com.edu.eam.myapplication.model.Estudiante

class EstudianteAdapter(var lista: ArrayList<Estudiante>, var context: Context): RecyclerView.Adapter<EstudianteAdapter.ViewHolder>(), Filterable{

    var copia:ArrayList<Estudiante> = lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindEstudiante(copia[position])
    }

    override fun getItemCount(): Int {
        return copia.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nombre: TextView = itemView.findViewById(R.id.nombre_estudiante)
        val codigo: TextView = itemView.findViewById(R.id.codigo_estudiante)
        val fecha: TextView = itemView.findViewById(R.id.fecha_estudiante)

        init{
            itemView.setOnClickListener(this)
        }

        fun bindEstudiante(estudiante: Estudiante){
            nombre.text = estudiante.nombre
            codigo.text = estudiante.codigo
            fecha.text = estudiante.fechaNacimiento.toString()
        }

        override fun onClick(v: View?) {
            Log.v("ESTUDIANTE", copia[adapterPosition].toString() )
            val i = Intent(context, DetalleActivity::class.java)
            i.putExtra("item_e", copia[adapterPosition].codigo)
            context.startActivity(i)
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val valor = constraint.toString()

                if(valor.isEmpty()) {
                    copia = lista
                } else {
                    copia = ArrayList()
                    val resultados = lista.filter { estudiante -> estudiante.nombre!!.lowercase().contains( valor.lowercase() ) }
                    copia.addAll(resultados)

                }
                return filterResults.also {
                    it.values = copia
                }

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                copia = results?.values as ArrayList<Estudiante>
                notifyDataSetChanged()
            }

        }
    }

    fun filtrar(s: String?) {
        filter.filter(s)
    }
}