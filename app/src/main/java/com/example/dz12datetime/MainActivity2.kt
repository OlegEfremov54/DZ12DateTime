package com.example.dz12datetime

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var toolbarPer: Toolbar
    private var person:Persons?=null
    private lateinit var personaNameET: TextView
    private lateinit var personaFamET: TextView
    private lateinit var personaDataET: TextView
    private lateinit var personaTelefonET: TextView
    private lateinit var editImageIV: ImageView
    private lateinit var buttonBackBTN: Button
    private var fotoUri: Uri? = null
    private lateinit var photoPickerLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarPer = findViewById(R.id.toolbarPer)
        setSupportActionBar(toolbarPer)
        title = "Карточка данных"
        toolbarPer.subtitle = "Версия1.Страница Персоны"
        toolbarPer.setLogo(R.drawable.persons_img)

        buttonBackBTN = findViewById(R.id.exitBTN)

        personaNameET = findViewById(R.id.personaNameET)
        personaFamET = findViewById(R.id.personaFam)
        personaDataET = findViewById(R.id.personaData)
        personaTelefonET=findViewById(R.id.personaTelefon)
        editImageIV=findViewById(R.id.editImageIV)

        person = (intent.extras?.getSerializable(Persons::class.java.simpleName) as Persons?)!!
        val name = person?.name.toString()
        val family = person?.family.toString()
        val besdata= person?.besdata.toString()
        val image = person?.image.toString()
        val telefon = person?.telefon.toString()

        val imageUri = Uri.parse(image)
        editImageIV.setImageURI(imageUri)

        personaNameET.text = name
        personaFamET.text = family
        personaTelefonET.text = telefon
        personaDataET.text = besdata

        //инициация картинки

        photoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    fotoUri = result.data?.data  // selectedImage для загрузки изображения
                    editImageIV.setImageURI(fotoUri)
                }
            }

        editImageIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            photoPickerLauncher.launch(photoPickerIntent)
        }


        buttonBackBTN.setOnClickListener {
            finishAffinity()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 23.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
