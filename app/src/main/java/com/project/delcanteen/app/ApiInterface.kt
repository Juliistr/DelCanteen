package com.project.delcanteen.app;

import com.project.delcanteen.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface{

    @GET("api/produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>

    @GET("api/produkkantinterbaru")
    fun getProdukKantinTerbaru(): Call<ResponProdukKantin>

    @GET("api/produkkoperasi")
    fun getProdukKoperasi(): Call<ResponProdukKoperasi>

    @GET("api/produkpulsa")
    fun getPulsa(): Call<ResponPulsa>

    @GET("api/ruangan")
    fun getPeminjamanRuangan(): Call<ResponPeminjamanRuangan>

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ):Call<ResponUser>

    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("name") name: String,
        @Field("no_ktp") no_ktp: String,
        @Field("no_hp") no_hp: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<ResponUser>

}

