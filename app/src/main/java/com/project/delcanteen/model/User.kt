package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("no_ktp")
    val no_ktp: String,
    @Expose
    @SerializedName("no_hp")
    val no_hp: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String

)