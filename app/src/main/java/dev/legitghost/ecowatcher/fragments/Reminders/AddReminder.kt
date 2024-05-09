package dev.legitghost.ecowatcher.fragments.Reminders

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.data.ViewModel.ReminderViewModel
import dev.legitghost.ecowatcher.databinding.FragmentAddReminderBinding
import java.time.LocalDateTime

class AddReminder : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding

    private lateinit var mReminderViewModel: ReminderViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddReminderBinding.inflate(inflater, container, false)

        binding.btnBackScreen.setOnClickListener {
            findNavController().navigate(R.id.action_addReminder_to_homeFragment)
        }

        mReminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)

        binding.createReminderButton.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root;
    }

    private fun insertDataToDatabase() {
        val reminderTitle = binding.reminderNameEditText.text.toString()

        val reminderYear = binding.datePicker.year
        val reminderMonth = binding.datePicker.month
        val reminderDay = binding.datePicker.dayOfMonth
        val reminderHour = binding.timePicker.hour
        val reminderMinute = binding.timePicker.minute

        val reminderFullDate = "$reminderYear-$reminderMonth-$reminderDay $reminderHour:$reminderMinute"

        if(inputCheck(reminderTitle, reminderYear, reminderMonth, reminderDay, reminderHour, reminderMinute, reminderFullDate)) {

            Log.d("lghost", reminderFullDate)
            val reminder = Reminder(0, reminderTitle, reminderYear, reminderMonth, reminderDay, reminderHour, reminderMinute, reminderFullDate)
            mReminderViewModel.addReminder(reminder)
            Toast.makeText(requireContext(), "Recordatorio agregado correctamente.", Toast.LENGTH_LONG).show()

            // Volver a la lista de recordatorios
            findNavController().navigate(R.id.action_addReminder_to_listReminderFragment)
        }else{
            Toast.makeText(requireContext(), "Debes llenar los campos vacíos.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(title: String, year: Int, month: Int, day: Int, hour: Int, minute: Int, reminderDateTime: String) : Boolean {
        return !(title.isEmpty() || year.equals(null) || month.equals(null) || day.equals(null) || hour.equals(null) || minute.equals(null)) || TextUtils.isEmpty(reminderDateTime)
    }
}