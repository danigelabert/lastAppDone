package com.danigelabert.lastappdone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Moble")
data class Moble(

    @ColumnInfo(name = "nom")
    var nom: String,
    @ColumnInfo(name = "preu")
    var preu: Int
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}