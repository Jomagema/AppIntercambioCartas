package com.example.appintercambiocartas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Publicacion(val titulo: String, val subtitulo: String)

class AdaptadorTitulares(
    private val datos: MutableList<Publicacion>,
    private val clickListener : (Publicacion) -> Unit) :
    RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>() {

    class TitularesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val lblTitulo = item.findViewById(R.id.txt2) as TextView

        fun bindTitular(titular: Publicacion){
            lblTitulo.text = titular.titulo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitularesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_titular, parent, false) as LinearLayout

        return TitularesViewHolder(item)
    }

    override fun onBindViewHolder(holder: TitularesViewHolder, position: Int) {
        val titular = datos[position]

        holder.bindTitular(titular)

        holder.item.setOnClickListener{clickListener(titular)};
    }

    override fun getItemCount() = datos.size
}