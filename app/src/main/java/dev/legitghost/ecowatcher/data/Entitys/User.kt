package dev.legitghost.ecowatcher.data.Entitys

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val age: Int
)
