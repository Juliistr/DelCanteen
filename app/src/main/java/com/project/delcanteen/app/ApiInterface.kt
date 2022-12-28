package com.project.delcanteen.app;

import com.project.delcanteen.model.ResponProdukKantin
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
    @GET("produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>
}

