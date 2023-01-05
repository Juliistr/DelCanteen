package com.project.delcanteen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.titleTextView.text = dataModel.nama_produk
        holder.priceTextView.text = dataModel.harga
        holder.TotalTextView.text = dataModel.jumlah
        holder.DescriptionTextView.text = dataModel.deskripsi
        holder.ImagesTextView.text = dataModel.gambar


    }

    class ViewHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var titleTextView: TextView
        lateinit var priceTextView: TextView
        lateinit var TotalTextView: TextView
        lateinit var DescriptionTextView: TextView
        lateinit var ImagesTextView: TextView

        init {
            titleTextView = itemLayoutView.findViewById(R.id.nama_produk)
            priceTextView = itemLayoutView.findViewById(R.id.harga)
            TotalTextView = itemLayoutView.findViewById(R.id.jumlah)
            DescriptionTextView = itemLayoutView.findViewById(R.id.deskripsi)
            ImagesTextView = itemLayoutView.findViewById(R.id.Gambar)
        }
    }
}