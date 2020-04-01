package com.example.introduccion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin_main2.*

class KotlinMain2Activity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    //Modo de acción contextual---------------------------------------------------------------------
    var actionMode : ActionMode? = null

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.menu_main2, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.salir -> {
                    startActivity(Intent(applicationContext, KotlinMainActivity::class.java))
                    mode.finish() // Action picked, so close the CAB
                    true
                }

                else -> false
            }
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main2)
        //Menú contextual flotante------------------------------------------------------------------
        //registerForContextMenu(tvLong)

        //Modo de acción contextual-----------------------------------------------------------------
        tvLong.setOnLongClickListener { view ->
            // Called when the user long-clicks on someView
            when (actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    actionMode = startActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                }
                else -> false
            }
        }
    }

    fun ConversorMoneda(view: View) {
        //Intención explícita
        startActivity(Intent(this, KotlinMainActivity::class.java))
    }

    fun EnviarMensaje(view: View) {
        //Intención implícita
        // Create the text message with a string
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, edtMensaje.text.toString())
            type = "text/plain"
        }
        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    fun MostrarMapa(view: View) {
        val gmmIntentUri: Uri = Uri.parse("geo:27.45331,-99.5179927?z=15")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    //Menú de opciones------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        //inflater.inflate(R.menu.menu_main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.salir -> {
                startActivity(Intent(this, KotlinMainActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //Menú contextual flotante----------------------------------------------------------------------
    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main2, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.salir -> {
                startActivity(Intent(this, KotlinMainActivity::class.java))
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    //Menú emergente--------------------------------------------------------------------------------
    fun showPopup(view: View) {
        PopupMenu(this, view).apply {
            // KotlinMain2Activity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@KotlinMain2Activity)
            inflate(R.menu.menu_main2)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.salir -> {
                startActivity(Intent(this, KotlinMainActivity::class.java))
                true
            }

            else -> false
        }
    }
}