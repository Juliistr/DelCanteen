package com.project.delcanteen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.model.PeminjamanRuangan

class PeminjamanRuanganAdapter(
    private var dataList: List<PeminjamanRuangan>, private val
    context: Context
) : RecyclerView.Adapter<PeminjamanRuanganAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_peminjaman_ruangan, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.titleTextView.text = dataModel.nama_Ruangan


    }

    class ViewHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var titleTextView: TextView

        init {
            titleTextView = itemLayoutView.findViewById(R.id.nama_ruangan)
        }
    }
}
