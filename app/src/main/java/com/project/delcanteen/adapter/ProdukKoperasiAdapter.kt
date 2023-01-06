package com.project.delcanteen.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.delcanteen.R
import com.project.delcanteen.activity.DetailProdukKantinActivity
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.ProdukKoperasi


class ProdukKoperasiAdapter(
    private var dataList: List<ProdukKoperasi>, private val
    context: Context
) : RecyclerView.Adapter<ProdukKoperasiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ProdukKoperasiAdapter.ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.titleTextView.text = dataModel.nama_produk
        holder.priceTextView.text = "Rp." + dataModel.harga
        holder.descriptionTextView.text = dataModel.deskripsi
        holder.jumlahTextView.text = dataModel.jumlah
        Glide.with(context).load(ApiClient.BASE_URL + dataModel.gambar).centerCrop()
            .into(holder.imageView)
//
        holder.imageView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailProdukKantinActivity::class.java)
            intent.putExtra("id", dataModel.id);
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var titleTextView: TextView
        lateinit var imageView: ImageView
        lateinit var descriptionTextView: TextView
        lateinit var priceTextView: TextView
        lateinit var jumlahTextView: TextView

        init {

            titleTextView = itemLayoutView.findViewById(R.id.nama_produk)
            imageView = itemLayoutView.findViewById<ImageView>(R.id.Gambar)
            descriptionTextView = itemLayoutView.findViewById(R.id.deskripsi)
            priceTextView = itemLayoutView.findViewById(R.id.harga)
            jumlahTextView = itemLayoutView.findViewById(R.id.jumlah)
        }
    }
}