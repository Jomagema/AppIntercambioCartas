package com.example.appintercambiocartas.Colecciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appintercambiocartas.R

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Colección Pokemon")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
    }
}