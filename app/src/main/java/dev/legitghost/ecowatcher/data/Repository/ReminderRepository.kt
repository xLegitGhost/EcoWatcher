package dev.legitghost.ecowatcher.data.Repository

import androidx.lifecycle.LiveData
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.data.database.ReminderDao

class ReminderRepository (private val reminderDao : ReminderDao) {

    val readAllData : LiveData<List<Reminder>> = reminderDao.getAllReminders()

    suspend fun addReminder(reminder: Reminder) {
        reminderDao.addReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminder) {
        reminderDao.updateReminder(reminder)
    }
    suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }

    fun getNextCollection(currentTime: String) : LiveData<Reminder> {
        return reminderDao.getNextCollection(currentTime)
    }


}