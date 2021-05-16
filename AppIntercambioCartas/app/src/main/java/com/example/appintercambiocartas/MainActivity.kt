package com.example.appintercambiocartas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnDBsuper: ImageButton
    private lateinit var btnMagic: ImageButton
    private lateinit var btnPokemon: ImageButton
    private lateinit var btnYugioh: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDBsuper=findViewById(R.id.btndbsuper)
        btnMagic=findViewById(R.id.btnmagic)
        btnPokemon=findViewById(R.id.btnpokemon)
        btnYugioh=findViewById(R.id.btnyugioh)

        btnDBsuper.setOnClickListener(this)
        btnMagic.setOnClickListener(this)
        btnPokemon.setOnClickListener(this)
        btnYugioh.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.getId()){
            R.id.btndbsuper -> startActivity(Intent(this, DBSuperActivity::class.java))
            R.id.btnmagic -> startActivity(Intent(this, MagicActivity::class.java))
            R.id.btnpokemon -> startActivity(Intent(this, PokemonActivity::class.java))
            R.id.btnyugioh -> startActivity(Intent(this, YugiohActivity::class.java))
        }
    }
}