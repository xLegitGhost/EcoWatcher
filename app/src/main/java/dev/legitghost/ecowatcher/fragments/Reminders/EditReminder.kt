package dev.legitghost.ecowatcher.fragments.Reminders

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.data.ViewModel.ReminderViewModel
import dev.legitghost.ecowatcher.databinding.FragmentEditReminderBinding

class EditReminder : Fragment() {

    private lateinit var binding : FragmentEditReminderBinding

    private val args by navArgs<EditReminderArgs>()
    private lateinit var mReminderViewModel : ReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mReminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)

        binding = FragmentEditReminderBinding.inflate(inflater, container, false)

        binding.etEditReminderName.setText(args.currentReminder.title)
        binding.editDatePicker.init(args.currentReminder.year, args.currentReminder.month-1, args.currentReminder.day, null)
        binding.timePicker.hour = args.currentReminder.hour
        binding.timePicker.minute = args.currentReminder.minute

        binding.editReminderButton.setOnClickListener {
            updateItem()
        }

        binding.deleteReminderButton.setOnClickListener {
            deleteItem()
        }

        binding.btnBackEditScreen.setOnClickListener {
            findNavController().navigate(R.id.action_editReminder2_to_listReminderFragment)
        }

        return binding.root
    }

    private fun updateItem() {
        val title = binding.etEditReminderName.text.toString()
        val year = binding.editDatePicker.year
        val month = binding.editDatePicker.month + 1
        val day = binding.editDatePicker.dayOfMonth
        val hour = binding.timePicker.hour
        val minute = binding.timePicker.minute
        val fullDate = "$year-$month-$day $hour:$minute"

        if(inputCheck(title, year, month, day, hour, minute, fullDate)) {
            val updatedReminder = Reminder(args.currentReminder.id, title, year, month, day, hour, minute, fullDate)
            // Update current reminder
            mReminderViewModel.updateReminder(updatedReminder)
            Toast.makeText(requireContext(), "Recordatorio actualizado :)", Toast.LENGTH_SHORT).show()
            // Volvemos a la lista de recordatorios
            findNavController().navigate(R.id.action_editReminder2_to_listReminderFragment)
        }
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sí") { _, _ ->
            mReminderViewModel.deleteReminder(args.currentReminder)
            Toast.makeText(requireContext(), "Recordatorio eliminado :(", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editReminder2_to_listReminderFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Eliminar ${args.currentReminder.title}?")
        builder.setMessage("¿Estás seguro de que deseas eliminar ${args.currentReminder.title}?")
        builder.create().show()
    }

    private fun inputCheck(title: String, year: Int, month: Int, day: Int, hour: Int, minute: Int, reminderDateTime: String) : Boolean {
        return !(title.isEmpty() || year.equals(null) || month.equals(null) || day.equals(null) || hour.equals(null) || minute.equals(null)) || TextUtils.isEmpty(reminderDateTime)
    }

}