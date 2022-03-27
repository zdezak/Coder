package com.zdez.coder.feature_user_list.domain.model

import com.squareup.moshi.Json


data class NetworkResponse(
    @Json(name = "items")
    val users: List<User>,
)