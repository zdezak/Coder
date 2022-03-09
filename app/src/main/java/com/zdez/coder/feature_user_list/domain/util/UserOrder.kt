package com.zdez.coder.feature_user_list.domain.util

sealed class UserOrder(val orderType: OrderType) {
    class FirstName(orderType: OrderType) : UserOrder(orderType)
    class Birthday(orderType: OrderType) : UserOrder(orderType)

    fun copy(orderType: OrderType): UserOrder {
        return when (this) {
            is FirstName -> FirstName(orderType)
            is Birthday -> Birthday(orderType)
        }
    }
}