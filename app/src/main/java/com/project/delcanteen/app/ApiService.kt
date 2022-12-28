package com.project.delcanteen.app

import com.project.delcanteen.model.ResponModel
import com.project.delcanteen.model.ResponProdukKantin
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
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ):Call<ResponModel>

    @GET("produkkantin")
    fun getProdukKantin(): Call<ResponProdukKantin>
}