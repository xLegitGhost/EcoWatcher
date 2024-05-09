package dev.legitghost.ecowatcher.fragments.Reminders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.data.Adapters.ListAdapter
import dev.legitghost.ecowatcher.data.ViewModel.ReminderViewModel

class ListReminderFragment : Fragment() {

    private lateinit var mReminderViewModel : ReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list_reminder, container, false)

        val btnAddReminder = view.findViewById<FloatingActionButton>(R.id.btnAddReminder)

        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.reminderListView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReminderViewModel
        mReminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        mReminderViewModel.readAllData.observe(viewLifecycleOwner, Observer { reminder ->
            adapter.setData(reminder)
        })

        btnAddReminder.setOnClickListener {
            findNavController().navigate(R.id.action_listReminderFragment_to_addReminder)
        }

       return view;
    }


}