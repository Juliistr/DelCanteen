package com.project.delcanteen.activity


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.adapter.ProdukKoperasiAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.ProdukKoperasi
import com.project.delcanteen.model.ResponProdukKoperasi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukKoperasiActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<ProdukKoperasi>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProdukKoperasiAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk_koperasi)
        recyclerView = findViewById(R.id.recycler_view_koperasi)
        //setting up the adapter
        recyclerView.adapter = ProdukKoperasiAdapter(dataList, this)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getProdukKoperasiData()
    }


    private fun getProdukKoperasiData() {
        val call: Call<ResponProdukKoperasi> =
            ApiClient.getClient.getProdukKoperasi()
        call.enqueue(object : Callback<ResponProdukKoperasi> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponProdukKoperasi>?,
                response: Response<ResponProdukKoperasi>?
            ) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponProdukKoperasi>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}