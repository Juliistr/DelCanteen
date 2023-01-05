package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeminjamanRuangan(
    @Expose
    @SerializedName("nama_ruangan")
    val nama_ruangan: String
)