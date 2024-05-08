package dev.legitghost.ecowatcher.fragments.Reminders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.databinding.FragmentListReminderBinding

class ListReminderFragment : Fragment() {

    private lateinit var binding : FragmentListReminderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListReminderBinding.inflate(inflater, container, false)

        binding.btnAddReminder.setOnClickListener {
            findNavController().navigate(R.id.action_listReminderFragment_to_addReminder)
        }


       return binding.root;
    }


}