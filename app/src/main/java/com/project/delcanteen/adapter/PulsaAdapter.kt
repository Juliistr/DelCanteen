package com.project.delcanteen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.delcanteen.R
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.Pulsa

class PulsaAdapter(
        private var dataList: List<Pulsa>, private val
        context: Context
    ) : RecyclerView.Adapter<PulsaAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup, viewType:
            Int
        ): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_pulsa, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.titleTextView.text = dataModel.nama_produk
        holder.priceTextView.text = dataModel.harga
        holder.jumlahTextView.text = dataModel.jumlah
        Glide.with(context).load(ApiClient.BASE_URL + dataModel.gambar).centerCrop()
            .into(holder.imageView)

    }

    class ViewHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var titleTextView: TextView
        lateinit var imageView: ImageView
        lateinit var priceTextView: TextView
        lateinit var jumlahTextView: TextView

        init {

            titleTextView = itemLayoutView.findViewById(R.id.nama_produk)
            imageView = itemLayoutView.findViewById<ImageView>(R.id.Gambar)
            priceTextView = itemLayoutView.findViewById(R.id.harga)
            jumlahTextView = itemLayoutView.findViewById(R.id.jumlah)
        }
    }
    }