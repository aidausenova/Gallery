package com.example.gallerychooser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gallery_detail.*

class GalleryDetailActivity : AppCompatActivity(),GalleryAdapter.ChooseListener {

    private var images = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setupAdapter()
    }

    private fun setupAdapter() {
        rv_detail.adapter = GalleryAdapter(images, chooseImg, this)
    }

    companion object {
        private var chooseImg = mutableListOf<Boolean>()
        fun start(activity: Activity?, chooseList: MutableList<Boolean>) {
            chooseImg = chooseList
            activity?.startActivity(Intent(activity, GalleryDetailActivity::class.java))
        }
    }

    override fun onItemClick(image: Int) {
    }
}