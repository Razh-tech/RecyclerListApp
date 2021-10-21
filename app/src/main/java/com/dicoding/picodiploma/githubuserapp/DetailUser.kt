package com.dicoding.picodiploma.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    val username: String,
    val fullName: String,
    val avatar: Int,
    val location: String,
    val company: String,
    val repository: String,
    val followers: String,
    val following: String
) : Parcelable
