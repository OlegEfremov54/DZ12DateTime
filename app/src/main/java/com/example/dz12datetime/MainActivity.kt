package com.example.dz12datetime

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var personaNameET:EditText
    private lateinit var personaFamET:EditText
    private lateinit var personaDataET:EditText
    private lateinit var personaTelefonET:EditText
    private lateinit var editImageIV: ImageView
    private lateinit var startButton:Button
    private var fotoUri: Uri? = null
    private lateinit var photoPickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Карточка данных"
        toolbarMain.subtitle = "Версия1.Главная страница"
        toolbarMain.setLogo(R.drawable.persons_img)

        personaNameET = findViewById(R.id.personaNameET)
        personaFamET = findViewById(R.id.personaFam)
        personaDataET = findViewById(R.id.personaData)
        personaTelefonET=findViewById(R.id.personaTelefon)
        startButton = findViewById(R.id.addBTN)
        editImageIV=findViewById(R.id.editImageIV)

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

//инициация кнопки перехода

        startButton.setOnClickListener {
            if (personaNameET.text.isEmpty() || personaFamET.text.isEmpty()) return@setOnClickListener
            val name = personaNameET.text.toString()
            val fam = personaFamET.text.toString()
            val image = fotoUri.toString()
            val fon = personaTelefonET.text.toString()
            val birthday = personaDataET.text.toString()

            val person = Persons(name, fam, birthday, image,fon)
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(Persons::class.java.simpleName, person)
            startActivity(intent)
            finish()

        }


    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.infoMenuMain -> {
                    Toast.makeText(
                        applicationContext, "Автор Ефремов О.В. Создан 23.11.2024",
                        Toast.LENGTH_LONG
                    ).show()
                }

                R.id.exitMenuMain -> {
                    Toast.makeText(
                        applicationContext, "Работа приложения завершена",
                        Toast.LENGTH_LONG
                    ).show()
                    finishAffinity()
                }
            }
            return super.onOptionsItemSelected(item)
        }

    }
