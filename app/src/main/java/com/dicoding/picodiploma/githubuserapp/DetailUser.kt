package com.dicoding.picodiploma.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    var username: String,
    var fullName: String,
    var avatar: Int,
    var location: String,
    var company: String,
    var repository: String,
    var followers: String,
    var following: String
) : Parcelable
