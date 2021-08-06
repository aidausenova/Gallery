package com.example.gallerychooser

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

@Suppress("UNREACHABLE_CODE")
class Helper {

    @SuppressLint("Recycle")
    fun listOfImages(context: Context): ArrayList<GImage> {

        val cursor: Cursor
        val column_index_data: Int
        val listOfAllImages: ArrayList<GImage> = ArrayList()
        var absolutePathOfImage: String
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            } else {
                TODO("VERSION.SDK_INT < Q")
            }
        val orderBy: String = MediaStore.Images.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy ASC")!!
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            listOfAllImages.add(GImage(absolutePathOfImage, false))
        }

        return listOfAllImages
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getFolderImages(context: Context):ArrayList<GImage> {
        val imagesList: java.util.ArrayList<GImage> = java.util.ArrayList()
        val folderPath: String? = null
        val folderName: String? = null
        val orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC"

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
            MediaStore.Images.Media.BUCKET_ID + " like ? AND " + MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " like ?",
            arrayOf("%$folderPath%", "%$folderName%"), orderBy
        )
        if (cursor?.moveToFirst()!!) {
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            do {
                val path = cursor.getString(dataColumn)
                val images = GImage()
                images.imageSet = path
                imagesList.add(images)
            } while (cursor.moveToNext())
        }
        return imagesList
        cursor.close()
    }
}