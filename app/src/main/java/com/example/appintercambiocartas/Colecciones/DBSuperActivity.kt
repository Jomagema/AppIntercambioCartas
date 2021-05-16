package com.example.appintercambiocartas.Colecciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appintercambiocartas.AdaptadorTitulares
import com.example.appintercambiocartas.Publicacion
import com.example.appintercambiocartas.R

class DBSuperActivity : AppCompatActivity() {

    private lateinit var recView : RecyclerView

    private lateinit var btnInsertar : Button
    private lateinit var btnEliminar : Button
    private lateinit var btnMover : Button
    private lateinit var db_createButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Colección Dragon Ball")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbsuper)

        btnInsertar = findViewById(R.id.btnInsertar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnMover = findViewById(R.id.btnMover)
        db_createButton = findViewById(R.id.db_createButton)

        recView = findViewById(R.id.recView)

        val datos =
            MutableList(50) { i -> Publicacion("Titulo $i", "Subtítulo Item $i") }

        val adaptador = AdaptadorTitulares(datos) {
            Log.i("DemoRecView", "Pulsado el elemento: ${it.titulo}")
        }

        recView.setHasFixedSize(true)

        //1. Linear Layout Manager
        recView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //2. Grid Layout Manager
        //recView.layoutManager = GridLayoutManager(this, 3)

        recView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        recView.itemAnimator = DefaultItemAnimator()

        recView.adapter = adaptador

        db_createButton.setOnClickListener{
            startActivity(Intent(this, CreateActivity::class.java))
        }

        btnInsertar.setOnClickListener {
            datos.add(1, Publicacion("Nuevo Titular", "Subtitulo Nuevo Titular"))
            adaptador.notifyItemInserted(1)

        }

        btnEliminar.setOnClickListener {
            datos.removeAt(1)
            adaptador.notifyItemRemoved(1)
        }

        btnMover.setOnClickListener {
            val titularAux = datos[1]
            datos[1] = datos[2].also { datos[2] = datos[1] }
            adaptador.notifyItemMoved(1, 2)
        }
    }

}
