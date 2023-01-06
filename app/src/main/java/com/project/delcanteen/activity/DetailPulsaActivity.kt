package com.project.delcanteen.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.delcanteen.R
import com.project.delcanteen.adapter.PulsaAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.Pulsa
import com.project.delcanteen.model.ResponPulsa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPulsaActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<Pulsa>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PulsaAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_pulsa)
        recyclerView = findViewById(R.id.recycler_view_pulsa_detail)
        //setting up the adapter
        recyclerView.adapter = PulsaAdapter(dataList, this)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getPulsaData()
    }


    private fun getPulsaData() {
        val call: Call<ResponPulsa> =
            ApiClient.getClient.getPulsa()
        call.enqueue(object : Callback<ResponPulsa> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponPulsa>?,
                response: Response<ResponPulsa>?
            ) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponPulsa>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}