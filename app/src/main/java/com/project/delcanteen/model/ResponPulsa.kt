package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponPulsa(
    @Expose
    @SerializedName("success")
    val success: String,
    @Expose
    @SerializedName("data")
    val data: ArrayList<Pulsa>
)