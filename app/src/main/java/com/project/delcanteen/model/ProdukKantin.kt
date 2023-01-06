package com.project.delcanteen.model;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProdukKantin(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("nama_produk")
    val nama_produk: String,
    @Expose
    @SerializedName("harga")
    val harga: String,
    @Expose
    @SerializedName("gambar")
    val gambar: String,
    @Expose
    @SerializedName("jumlah")
    val jumlah: String,
    @Expose
    @SerializedName("deskripsi")
    val deskripsi: String
)
