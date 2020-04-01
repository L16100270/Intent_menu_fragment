package com.example.introduccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity(), EliminarJugadorDialogFragment.EliminarJugadorDialogListener {

    val onLongItemClickListener: (Int) -> Unit = {position ->
//        Toast.makeText(this, "Eliminar a ${Singleton.dataSet.get(position).control}", Toast.LENGTH_LONG).show()
        DialogEliminarJugador(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        LoadData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = JugadoresAdapter(onLongItemClickListener)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main2, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.salir -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun LoadData() {
        for(x in 0..5) {
            Singleton.dataSet.add(Jugador("${x.toString()}","Jugador #${x}","${if(x%2==0) "New York Yankees" else "San Francisco Giants"}",true))
        }
    }

    private fun DialogEliminarJugador(position: Int) {
        val dialog = EliminarJugadorDialogFragment(position)
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    override fun onDialogPositiveClick(position: Int) {
        val jugador = Singleton.dataSet.get(position)
        Singleton.dataSet.removeAt(position)
        //recyclerView.adapter?.notifyItemRemoved(position)
        recyclerView.adapter?.notifyDataSetChanged()

//        Snackbar.make(recyclerView, "Alumno eliminado ${alumno.control}", Snackbar.LENGTH_LONG)
//                .setAction("Deshacer") {
//                    Singleton.dataSet.add(position, alumno)
//                    recyclerView.adapter?.notifyItemInserted(position)
//                }.show()

        Snackbar.make(recyclerView, "Jugador # ${jugador.numeroJugador} Eliminado", Snackbar.LENGTH_LONG)
                .setAction("Cancelar", {
                    Singleton.dataSet.add(position, jugador)
                    //recyclerView.adapter?.notifyItemInserted(position)
                    recyclerView.adapter?.notifyDataSetChanged()  //deshace ek cambio
                }).show()
    }

    override fun onDialogNegativeClick(position: Int) {
        val jugador = Singleton.dataSet.get(position)
        Toast.makeText(this, "El jugador ${jugador.numeroJugador} no fue eliminado", Toast.LENGTH_SHORT).show()
    }
}
