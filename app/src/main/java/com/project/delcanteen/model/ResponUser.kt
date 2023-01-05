package com.project.delcanteen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponUser(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: ArrayList<User>
)