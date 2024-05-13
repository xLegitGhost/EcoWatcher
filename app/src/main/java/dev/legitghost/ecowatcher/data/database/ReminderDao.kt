package dev.legitghost.ecowatcher.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.legitghost.ecowatcher.data.Entitys.Reminder

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReminder(reminder: Reminder)

    @Query("SELECT * FROM reminder_table ORDER BY id ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Update
    suspend fun updateReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("SELECT * FROM reminder_table WHERE timeDate > :currentTime ORDER BY timeDate ASC LIMIT 1")
    fun getNextCollection(currentTime: String): LiveData<Reminder>


}