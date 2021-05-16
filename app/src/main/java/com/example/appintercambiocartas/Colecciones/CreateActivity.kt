package com.example.appintercambiocartas.Colecciones

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appintercambiocartas.R


class CreateActivity : AppCompatActivity() {

    private val REQUEST_GALLERY = 1001
    private val REQUEST_CAMARA = 1002

    private lateinit var btnCamara: ImageButton

    private lateinit var tituloTxt: EditText
    private lateinit var descripcionTxt: EditText

    private lateinit var fotoImageview: ImageView

    var foto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Crear publicacion")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        btnCamara = findViewById(R.id.camaraButton)
        tituloTxt = findViewById(R.id.tituloEditText)
        descripcionTxt = findViewById(R.id.descripcionEditText)
        fotoImageview = findViewById(R.id.fotoImageView)

        btnCamara.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                    val permisosCamara = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisosCamara, REQUEST_CAMARA)

                }
            }else{
                abrirCamara()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CAMARA ->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    abrirCamara()
                else
                    Toast.makeText(applicationContext, "No puedes acceder a la camara", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun abrirCamara(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(camaraIntent, REQUEST_CAMARA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMARA){
            fotoImageview.setImageURI(foto)
        }
    }
}