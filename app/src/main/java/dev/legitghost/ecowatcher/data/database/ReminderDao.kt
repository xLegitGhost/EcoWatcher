package dev.legitghost.ecowatcher.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.legitghost.ecowatcher.data.Entitys.Reminder

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReminder(reminder: Reminder)

    @Query("SELECT * FROM reminder_table ORDER BY id ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("DELETE FROM reminder_table WHERE id = :id")
    suspend fun deleteReminder(id: Int)



}