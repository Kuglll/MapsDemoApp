package com.example.mapsdemoapp.data.network.models

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("address")
    val address: Address,
)

data class Address(
    @SerializedName("village")
    val village: String,
    @SerializedName("city")
    val city: String,
)
