package com.project.delcanteen.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
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
import com.project.delcanteen.adapter.ProdukKantinAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.ProdukKantin
import com.project.delcanteen.model.ResponSingleProdukKantin
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProdukKantinActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var data: ProdukKantin? = null
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProdukKantinAdapter
    lateinit var gambarProduk: ImageView
    lateinit var namaProduk: TextView
    lateinit var harga: TextView
    lateinit var stok: TextView
    lateinit var deskripsi: TextView
    lateinit var pesanBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        gambarProduk = findViewById(R.id.imageViewDetail)
        namaProduk = findViewById(R.id.textViewNamaDetail)
        harga = findViewById(R.id.textViewHargaDetail)
        stok = findViewById(R.id.textViewStokDetail)
        deskripsi = findViewById(R.id.textViewDeskripsiDetail)
        pesanBtn = findViewById(R.id.btn_pesan)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        val intent = intent
        val id = intent.getStringExtra("id")
        Log.i("Id", id.toString())
        getProdukKantinDataById(id)
        pesanBtn.setOnClickListener { view ->
            val intent = Intent(view.context, PembayaranActivity::class.java)
            intent.putExtra("nama_produk", data!!.nama_produk);
            intent.putExtra("harga", data!!.harga);
            startActivity(intent)
        }
    }


    private fun getProdukKantinDataById(id: String?) {
        val call: Call<ResponSingleProdukKantin> =
            ApiClient.getClient.getProdukKantinById(id)
        call.enqueue(object : Callback<ResponSingleProdukKantin> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponSingleProdukKantin>?,
                response: Response<ResponSingleProdukKantin>?
            ) {
                progerssProgressDialog.dismiss()
                data = response!!.body()!!.data
                Glide.with(applicationContext).load(ApiClient.BASE_URL + data!!.gambar).centerCrop()
                    .into(gambarProduk)
                namaProduk.text = data!!.nama_produk
                harga.text = "Rp. " + data!!.harga
                stok.text = data!!.jumlah
                deskripsi.text = data!!.deskripsi

            }

            override fun onFailure(
                call: Call<ResponSingleProdukKantin>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}