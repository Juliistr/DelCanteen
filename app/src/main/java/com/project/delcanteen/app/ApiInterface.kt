package com.project.delcanteen.app;

import com.project.delcanteen.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("api/produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>

    @GET("api/produkkantin/{id}")
    fun getProdukKantinById(
        @Path("id") id: String?
    ): Call<ResponSingleProdukKantin>

    @GET("api/produkkantinterbaru")
    fun getProdukKantinTerbaru(): Call<ResponProdukKantin>

    @GET("api/produkkoperasi")
    fun getProdukKoperasi(): Call<ResponProdukKoperasi>

    @GET("api/produkkoperasi/{id}")
    fun getProdukKopersiById(
        @Path("id") id: String?
    ): Call<ResponSingleProdukKoperasi>

    @GET("api/produkpulsa")
    fun getPulsa(): Call<ResponPulsa>

    @GET("api/ruangan")
    fun getPeminjamanRuangan(): Call<ResponPeminjamanRuangan>

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponUser>

    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("name") name: String,
        @Field("no_ktp") no_ktp: String,
        @Field("no_hp") no_hp: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponUser>

}

