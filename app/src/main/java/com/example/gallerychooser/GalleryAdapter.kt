package com.example.gallerychooser

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gallery_item.view.*
import java.util.*

class GalleryAdapter(
    var list: MutableList<Int>,
    var chooserImg: MutableList<Boolean>,
    var listener: ChooseListener
) : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    lateinit var arrayList: ArrayList<String>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.GalleryHolder {
        return GalleryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GalleryAdapter.GalleryHolder, position: Int) {
        holder.onBind(list[position])

        if (chooserImg[position])
            holder.itemView.choose.visibility = View.VISIBLE
        else
            holder.itemView.choose.visibility = View.GONE

        holder.itemView.setOnClickListener {
            if (chooserImg[position]){
                chooserImg[position]=false
            }
            listener.onItemClick(list[position])
            notifyDataSetChanged()
        }
    }

    class GalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: Int) {
            itemView.image_rv.setImageURI(Uri.parse(item.toString()))
        }
    }

    interface ChooseListener {
        fun onItemClick(image: Int)
    }

}