package com.example.gallerychooser

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(), GalleryAdapter.ChooseListener {
    private var chooserImg = mutableListOf<Boolean>()
    private val KEY = 100

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isHasPermission()

        btn_send.setOnClickListener {
            GalleryDetailActivity.start(this, chooserImg)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun isHasPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                KEY
            )
        } else
            setupAdapter()

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setupAdapter() {
//        val list = Helper().getFolderImages(this)
        val list = mutableListOf<Int>(R.drawable.prince)
        rv_main.adapter = GalleryAdapter(list, chooserImg, this)
    }

    override fun onItemClick(image: Int) {
        Toast.makeText(this, "Choosed", Toast.LENGTH_SHORT).show()
    }

    /*
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == KEY) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setupAdapter()
        }
    }
    */

}