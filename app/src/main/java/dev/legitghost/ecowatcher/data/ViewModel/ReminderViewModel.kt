package dev.legitghost.ecowatcher.data.ViewModel

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.legitghost.ecowatcher.AlarmNotification
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.data.Repository.ReminderRepository
import dev.legitghost.ecowatcher.data.database.ReminderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ReminderViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Reminder>>
    private val repository : ReminderRepository

    init {
        val userDao = ReminderDatabase.getDatabase(application).reminderDao()
        repository = ReminderRepository(userDao)
        readAllData = repository.readAllData
    }


    @SuppressLint("ScheduleExactAlarm")
    fun addReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addReminder(reminder)

            // Programa la alarma después de agregar el recordatorio
            val reminderTime = LocalDateTime.of(reminder.year, reminder.month, reminder.day, reminder.hour, reminder.minute)
            val alarmManager = getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(getApplication(), AlarmNotification::class.java)
            val pendingIntent = PendingIntent.getBroadcast(getApplication(), reminder.id, intent,
                PendingIntent.FLAG_IMMUTABLE)

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                reminderTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                pendingIntent
            )

            Log.d("lghost", "Alarma programada para ${reminderTime}")
            Log.d("lghost", "Milisegundos ${reminderTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()}")
        }
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReminder(reminder)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteReminder(reminder)

            // Cancela la alarma después de eliminar el recordatorio
            val intent = Intent(getApplication(), AlarmNotification::class.java)
            val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(getApplication(), reminder.id, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT)
            } else {
                PendingIntent.getBroadcast(getApplication(), reminder.id, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            }

            val alarmManager = getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }


    fun getNextCollection(): LiveData<Reminder> {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        return repository.getNextCollection(currentTime)
    }

}