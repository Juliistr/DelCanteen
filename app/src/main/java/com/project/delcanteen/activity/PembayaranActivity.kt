package com.project.delcanteen.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.delcanteen.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {
    lateinit var namaProduk: TextView
    lateinit var hargaProduk: TextView
    lateinit var pesanBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)
        val intent = intent
        val nama_produk = intent.getStringExtra("nama_produk")
        val harga_produk = intent.getStringExtra("harga")
        namaProduk = findViewById(R.id.nama_produk)
        hargaProduk = findViewById(R.id.harga_produk)
        pesanBtn = findViewById(R.id.btn_pemesanan)
        namaProduk.text = nama_produk
        hargaProduk.text = "Rp." + harga_produk

        pesanBtn.setOnClickListener {
            Toast.makeText(
                this@PembayaranActivity,
                "Pemesanan berhasil, Terima kasih!!",
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}