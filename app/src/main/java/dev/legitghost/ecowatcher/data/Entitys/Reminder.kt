package dev.legitghost.ecowatcher.data.Entitys

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Contains the data structure for the user table

@Parcelize
@Entity(tableName = "reminder_table")
data class Reminder (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "day") val day: Int,
    @ColumnInfo(name = "hour") val hour: Int,
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "timeDate") val timeDate: String
) : Parcelable