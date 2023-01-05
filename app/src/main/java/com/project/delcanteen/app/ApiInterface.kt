package com.project.delcanteen.app;

import com.project.delcanteen.model.ResponPeminjamanRuangan
import com.project.delcanteen.model.ResponProdukKantin
import com.project.delcanteen.model.ResponProdukKoperasi
import com.project.delcanteen.model.ResponPulsa
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
    @GET("api/produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>

    @GET("api/produkkoperasi")
    fun getProdukKoperasi(): Call<ResponProdukKoperasi>

    @GET("api/ruangan")
    fun getPeminjamanRuangan(): Call<ResponPeminjamanRuangan>

    @GET("api/produkpulsa")
    fun getPulsa(): Call<ResponPulsa>

}

