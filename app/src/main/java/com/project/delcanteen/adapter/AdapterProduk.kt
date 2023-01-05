package com.project.delcanteen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.model.ProdukKantin

class AdapterProduk(var data: ArrayList<ProdukKantin>) :
    RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.nama_produk)
        val tvHarga = view.findViewById<TextView>(R.id.harga)
        val imgProduk = view.findViewById<ImageView>(R.id.Gambar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_produk_kantin, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].nama_produk
        holder.tvHarga.text = data[position].harga
//        holder.imgProduk.setImageResource(data[position].gambar)
    }

}