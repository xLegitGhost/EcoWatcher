package dev.legitghost.ecowatcher.data.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.legitghost.ecowatcher.R
import dev.legitghost.ecowatcher.data.Entitys.Reminder
import dev.legitghost.ecowatcher.fragments.Reminders.ListReminderFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var reminderList = emptyList<Reminder>()

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reminder_template, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = reminderList[position]

        lateinit var time: String

        if(currentItem.minute == 0){
            time = "${currentItem.hour}:00"
        }
        if(currentItem.minute.toString().length == 1){
            time = "${currentItem.hour}:0${currentItem.minute}"
        }
        if(currentItem.minute.toString().length == 2){
            time = "${currentItem.hour}:${currentItem.minute}"
        }

        val date = "${currentItem.day}/${currentItem.month}/${currentItem.year}"

        val tvReminderId = holder.itemView.findViewById<TextView>(R.id.tvReminderId)
        val tvReminderTitle = holder.itemView.findViewById<TextView>(R.id.tvReminderTitle)
        val tvReminderTime = holder.itemView.findViewById<TextView>(R.id.tvTime)
        val tvReminderDate = holder.itemView.findViewById<TextView>(R.id.tvDate)

        tvReminderId.text = currentItem.id.toString()
        tvReminderTitle.text = currentItem.title
        tvReminderTime.text = time
        tvReminderDate.text = date

        val rowReminder = holder.itemView.findViewById<ConstraintLayout>(R.id.rowReminder)
        rowReminder.setOnClickListener {
            val action = ListReminderFragmentDirections.actionListReminderFragmentToEditReminder2(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setData(reminder: List<Reminder>){
        this.reminderList = reminder
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reminderList.size

    }
}
