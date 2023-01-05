package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeminjamanRuangan(
    @Expose
    @SerializedName("nama_Ruangan")
    val nama_Ruangan: String
)