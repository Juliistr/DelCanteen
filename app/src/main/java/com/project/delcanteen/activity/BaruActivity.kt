package com.project.delcanteen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.project.delcanteen.R

class BaruActivity : AppCompatActivity() {
    lateinit var namaProduk: TextView
    lateinit var harga: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baru)
        val intent = intent
        val nama_produk = intent.getStringExtra("nama_produk")
        val harga = intent.getStringExtra("harga")
    }
}