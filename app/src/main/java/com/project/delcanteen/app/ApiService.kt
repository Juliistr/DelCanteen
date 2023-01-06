package com.project.delcanteen.app

import com.project.delcanteen.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("KTP") KTP: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<ResponUser>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ):Call<ResponUser>

    @GET("produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>

    @GET("produkkoperasi")
    fun getProdukKoperasi(): Call<ResponProdukKoperasi>

    @GET("produkpulsa")
    fun getPulsa(): Call<ResponPulsa>

    @GET("ruangan")
    fun getPeminjamanRuangan(): Call<ResponPeminjamanRuangan>
}