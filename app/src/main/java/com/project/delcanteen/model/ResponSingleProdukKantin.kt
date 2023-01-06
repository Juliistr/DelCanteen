package com.project.delcanteen.model;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponSingleProdukKantin(
    @Expose
    @SerializedName("success")
    val success: String,
    @Expose
    @SerializedName("data")
    val data: ProdukKantin
)