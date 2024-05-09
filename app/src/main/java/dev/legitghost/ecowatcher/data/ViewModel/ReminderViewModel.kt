package dev.legitghost.ecowatcher.data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.data.Repository.ReminderRepository
import dev.legitghost.ecowatcher.data.database.ReminderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Reminder>>
    private val repository : ReminderRepository

    init {
        val userDao = ReminderDatabase.getDatabase(application).reminderDao()
        repository = ReminderRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addReminder(reminder)
        }
    }

}