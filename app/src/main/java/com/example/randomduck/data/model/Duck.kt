package com.example.randomduck.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Duck(
    val url: String
) : Parcelable