package com.project.delcanteen.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.delcanteen.R
import com.project.delcanteen.adapter.PulsaAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPulsaActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var data: Pulsa? = null
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PulsaAdapter
    lateinit var gambarProduk: ImageView
    lateinit var namaProduk: TextView
    lateinit var harga: TextView
    lateinit var stok: TextView
    lateinit var pesanBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pulsa)
        gambarProduk = findViewById(R.id.imageViewDetailPulsa)
        namaProduk = findViewById(R.id.textViewNamaDetailPulsa)
        harga = findViewById(R.id.textViewHargaDetailPulsa)
        stok = findViewById(R.id.textViewStokDetailPulsa)
        pesanBtn = findViewById(R.id.btn_pesan)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        val intent = intent
        val id = intent.getStringExtra("id")
        Log.i("Id", id.toString())
        getPulsaDataById(id)
        pesanBtn.setOnClickListener { view ->
            val intent = Intent(view.context, PembayaranActivity::class.java)
            intent.putExtra("nama_produk", data!!.nama_produk);
            intent.putExtra("harga", data!!.harga);
            startActivity(intent)
        }
    }


    private fun getPulsaDataById(id: String?) {
        val call: Call<ResponSinglePulsa> =
            ApiClient.getClient.getPulsaById(id)
        call.enqueue(object : Callback<ResponSinglePulsa> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponSinglePulsa>?,
                response: Response<ResponSinglePulsa>?
            ) {
                progerssProgressDialog.dismiss()
                data = response!!.body()!!.data
                Glide.with(applicationContext).load(ApiClient.BASE_URL + data!!.gambar).centerCrop()
                    .into(gambarProduk)
                namaProduk.text = data!!.nama_produk
                harga.text = "Rp. " + data!!.harga
                stok.text = data!!.jumlah

            }

            override fun onFailure(
                call: Call<ResponSinglePulsa>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}