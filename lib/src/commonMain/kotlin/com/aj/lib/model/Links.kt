package com.aj.lib.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Links(
    @SerialName("mission_patch")
    val missionPatchUrl: String?,
    @SerialName("article_link")
    val articleUrl: String?
)