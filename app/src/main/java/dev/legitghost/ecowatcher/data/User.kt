package dev.legitghost.ecowatcher.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Contains the data structure for the user table


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val age: Int
)
