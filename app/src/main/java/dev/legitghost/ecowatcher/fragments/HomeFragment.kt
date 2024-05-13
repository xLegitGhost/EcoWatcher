package dev.legitghost.ecowatcher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.legitghost.ecowatcher.data.ViewModel.ReminderViewModel
import dev.legitghost.ecowatcher.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mReminderViewModel: ReminderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val tvNextRecolection = binding.tvNextRecolection



        // ViewModel
        mReminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        // Observar el siguiente recordatorio segun el metodo getNextCollection
        mReminderViewModel.getNextCollection().observe(viewLifecycleOwner, Observer { reminder ->

            lateinit var hora: String


            if(reminder != null){

                val dia = getDayOfWeek(reminder.year, reminder.month, reminder.day)

                if(reminder.minute.toString().length == 1){
                    hora = "${reminder.hour}:0${reminder.minute}"
                }
                if(reminder.minute.toString().length == 2){
                    hora = "${reminder.hour}:${reminder.minute}"
                }

                // Convertir el mes a texto
                val mes = when(reminder.month){
                    1 -> "Enero"
                    2 -> "Febrero"
                    3 -> "Marzo"
                    4 -> "Abril"
                    5 -> "Mayo"
                    6 -> "Junio"
                    7 -> "Julio"
                    8 -> "Agosto"
                    9 -> "Septiembre"
                    10 -> "Octubre"
                    11 -> "Noviembre"
                    12 -> "Diciembre"
                    else -> "Invalido"
                }

                tvNextRecolection.text = "$dia ${reminder.day} de $mes ${reminder.year} a las $hora"
            }else{
                tvNextRecolection.text = "No hay recordatorios"
            }
        })

        return binding.root;
    }

    private fun getDayOfWeek(year: Int, month: Int, day: Int): String {
        val days = arrayOf("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado")
        val date = java.util.Calendar.getInstance()
        date.set(year, month, day)
        val dayOfWeek = date.get(java.util.Calendar.DAY_OF_WEEK)
        return days[dayOfWeek]
    }


}