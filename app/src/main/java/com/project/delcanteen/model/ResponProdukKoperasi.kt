package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.delcanteen.adapter.ProdukKantinAdapter

data class ResponProdukKoperasi(
    @Expose
    @SerializedName("success")
    val success: String,
    @Expose
    @SerializedName("data")
    val data: ArrayList<ProdukKoperasi>
)