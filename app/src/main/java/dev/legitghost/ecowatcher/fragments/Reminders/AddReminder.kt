package dev.legitghost.ecowatcher.fragments.Reminders

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.databinding.FragmentAddReminderBinding
import java.time.LocalDateTime

class AddReminder : Fragment() {

    private lateinit var binding : FragmentAddReminderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddReminderBinding.inflate(inflater, container, false)

        binding.btnBackScreen.setOnClickListener {
            findNavController().popBackStack()
        }



        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.createReminderButton.setOnClickListener{
            val reminderTitle = binding.reminderNameEditText.text.toString()
            val reminderDateTime = LocalDateTime.of(
                binding.datePicker.year,
                binding.datePicker.month,
                binding.datePicker.dayOfMonth,
                binding.timePicker.hour,
                binding.timePicker.minute
            )

            val reminderYear = binding.datePicker.year
            val reminderMonth = binding.datePicker.month
            val reminderDay = binding.datePicker.dayOfMonth
            val reminderHour = binding.timePicker.hour
            val reminderMinute = binding.timePicker.minute

            findNavController().navigate(R.id.action_addReminder_to_homeFragment)



            Log.d("lghost", "Reminder Title: $reminderTitle")
            Log.d("lghost", "Reminder Date Time: $reminderDateTime")



            // Guardar data al objeto y mandarla a la base de datos
            //val trashReminder = TrashReminder(reminderName, reminderDateTime)
        }
    }

}