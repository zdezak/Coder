package com.zdez.coder.feature_user_list.domain.util

sealed class UserOrder(val sortType: SortType, val orderType: OrderType) {
    class FirstName(sortType: SortType, orderType: OrderType) : UserOrder(sortType, orderType)
    class Birthday(sortType: SortType, orderType: OrderType) : UserOrder(sortType, orderType)

    fun copy(orderType: OrderType): UserOrder {
        return when (this) {
            is FirstName -> FirstName(sortType, orderType)
            is Birthday -> Birthday(sortType, orderType)
        }
    }
}