package com.project.delcanteen.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.adapter.ProdukKantinAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.ProdukKantin
import com.project.delcanteen.model.ResponProdukKantin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukKantinActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<ProdukKantin>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProdukKantinAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk_kantin)
        recyclerView = findViewById(R.id.recycler_view)
        //setting up the adapter
        recyclerView.adapter = ProdukKantinAdapter(dataList, this)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getProdukKantinData()
    }


    private fun getProdukKantinData() {
        val call: Call<ResponProdukKantin> =
            ApiClient.getClient.getProdukKantin()
        call.enqueue(object : Callback<ResponProdukKantin> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponProdukKantin>?,
                response: Response<ResponProdukKantin>?
            ) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponProdukKantin>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}