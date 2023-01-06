package com.project.delcanteen.activity


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.adapter.PeminjamanRuanganAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.PeminjamanRuangan
import com.project.delcanteen.model.ResponPeminjamanRuangan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RuanganActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<PeminjamanRuangan>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PeminjamanRuanganAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_peminjaman_ruangan)
        recyclerView = findViewById(R.id.recycler_view_ruangan_detail)
        //setting up the adapter
        recyclerView.adapter = PeminjamanRuanganAdapter(dataList, this)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getProdukKoperasiData()
    }


    private fun getProdukKoperasiData() {
        val call: Call<ResponPeminjamanRuangan> =
            ApiClient.getClient.getPeminjamanRuangan()
        call.enqueue(object : Callback<ResponPeminjamanRuangan> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponPeminjamanRuangan>?,
                response: Response<ResponPeminjamanRuangan>?
            ) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponPeminjamanRuangan>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}