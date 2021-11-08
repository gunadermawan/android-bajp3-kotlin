package com.gunder.film.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "listentities")
data class ListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "images")
    var images: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "release_date")
    var release_date: String? = null,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean? = false,

    @ColumnInfo(name = "type")
    var type: String? = null
) : Parcelable