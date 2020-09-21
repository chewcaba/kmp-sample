package com.aj.lib.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Rocket(
    @SerialName("rocket_id")
    val id: String,
    @SerialName("rocket_name")
    val name: String,
    @SerialName("rocket_type")
    val type: String
)