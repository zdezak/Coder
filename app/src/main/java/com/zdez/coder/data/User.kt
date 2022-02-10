package com.zdez.coder.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "people")
data class User(

    @Json(name = "id")
    @PrimaryKey
    val id: String,

    @Json(name = "avatarUrl")
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String,

    @Json(name = "firstName")
    @ColumnInfo(name = "firstName")
    val firstName: String,

    @Json(name = "lastName")
    @ColumnInfo(name = "lastName")
    val lastName: String,

    @Json(name = "userTag")
    @ColumnInfo(name = "userTag")
    val userTag: String,

    @Json(name = "department")
    @ColumnInfo(name = "department")
    val department: String,

    @Json(name = "position")
    @ColumnInfo(name = "position")
    val position: String,

    @Json(name = "birthday")
    @ColumnInfo(name = "birthday")
    val birthday: String,

    @Json(name = "phone")
    @ColumnInfo(name = "phone")
    val phone: String,
)
