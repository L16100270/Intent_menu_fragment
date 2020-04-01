package com.example.introduccion

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.jugadores_recyclerview_item.view.*

class JugadoresAdapter(private val longItemClickistener: (Int) -> Unit) :
        RecyclerView.Adapter<JugadoresAdapter.ViewHolder>(){

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvNumJugador = v.tvNumJugador
        val tvNombre = v.tvNombre
        val tvEquipo = v.tvEquipo
        val ivLogo = v.ivLogo
        val tvActivo = v.tvActivo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.jugadores_recyclerview_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, dataSet.get(position).nombre, Toast.LENGTH_LONG).show()
            val intent = Intent(holder.itemView.context, DatosJugadorActivity:: class.java)
            intent.putExtra("numeroJugador", Singleton.dataSet.get(position).numeroJugador)
            intent.putExtra("nombre", Singleton.dataSet.get(position).nombre)
            intent.putExtra("Equipo",Singleton.dataSet.get(position).Equipo)
            intent.putExtra("activo",Singleton.dataSet.get(position).Activo)
            holder.itemView.context.startActivity(intent)

        }

        holder.itemView.setOnLongClickListener {
            longItemClickistener.invoke(position)
            true
        }

        holder.tvNumJugador.text = Singleton.dataSet.get(position).numeroJugador
        holder.tvNombre.text = Singleton.dataSet.get(position).nombre
        holder.tvEquipo.text = Singleton.dataSet.get(position).Equipo
        if (Singleton.dataSet.get(position).Activo){
            holder.tvActivo.text = "En Activo"
        }else{
            holder.tvActivo.text = "En Reserva"
        }
         when (Singleton.dataSet.get(position).Equipo.toString()){
             "Chicago CUBS"         -> holder.ivLogo.setImageResource(R.drawable.cubs)
             "New York Yankees"     -> holder.ivLogo.setImageResource(R.drawable.yankees)
             "Boston Red Sox"       -> holder.ivLogo.setImageResource(R.drawable.red_sox)
             "San Francisco Giants" -> holder.ivLogo.setImageResource(R.drawable.giants)
                 else ->{

                 }
         }
    }
}