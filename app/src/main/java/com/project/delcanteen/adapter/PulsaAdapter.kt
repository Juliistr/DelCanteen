package com.project.delcanteen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
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
            holder.TotalTextView.text = dataModel.jumlah
            holder.ImagesTextView.text = dataModel.gambar


        }

        class ViewHolder(itemLayoutView: View) :
            RecyclerView.ViewHolder(itemLayoutView) {
            lateinit var titleTextView: TextView
            lateinit var priceTextView: TextView
            lateinit var TotalTextView: TextView
            lateinit var ImagesTextView: TextView

            init {
                titleTextView = itemLayoutView.findViewById(R.id.nama_produk)
                priceTextView = itemLayoutView.findViewById(R.id.harga)
                TotalTextView = itemLayoutView.findViewById(R.id.jumlah)
                ImagesTextView = itemLayoutView.findViewById(R.id.Gambar)
            }
        }

    }