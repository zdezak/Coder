package com.zdez.coder.feature_user_list.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}