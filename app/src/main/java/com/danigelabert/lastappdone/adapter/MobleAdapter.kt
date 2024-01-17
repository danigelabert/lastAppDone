package com.danigelabert.lastappdone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danigelabert.lastappdone.R
import com.danigelabert.lastappdone.model.Moble

class MobleAdapter(private val mobles: List<Moble>, private val onItemClick: (Moble) -> Unit) :
    RecyclerView.Adapter<MobleAdapter.MobleViewHolder>() {

    // ViewHolder que contiene las vistas para cada elemento
    class MobleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nom: TextView = view.findViewById(R.id.textViewNombre)
        val preu: TextView = view.findViewById(R.id.textViewPrecio)
    }

    // Crea una nueva vista (invocada por el layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview, parent, false) // Usa tu CardView aquí
        return MobleViewHolder(view)
    }

    // Reemplaza el contenido de una vista (invocada por el layout manager)
    override fun onBindViewHolder(holder: MobleViewHolder, position: Int) {
        val moble = mobles[position]

        holder.itemView.setOnClickListener {
            onItemClick.invoke(moble)
        }

        holder.nom.text = moble.nom
        holder.preu.text = moble.preu.toString() + "€"
    }

    // Devuelve el tamaño de tu conjunto de datos (invocada por el layout manager)
    override fun getItemCount() = mobles.size
}