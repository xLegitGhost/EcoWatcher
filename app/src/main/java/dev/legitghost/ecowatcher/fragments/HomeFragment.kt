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

    private var titleTips = arrayOf(
        "Recicla y reutiliza",
        "Ahorra energía",
        "Reduce el uso del automóvil",
        "Ahorra agua",
        "Compra productos locales y de temporada",
        "Planta árboles",
        "Evita los productos con mucho embalaje",
        "Composta tus residuos orgánicos",
        "Educa a otros",
        "Participa en la limpieza de tu comunidad"
    )

    private val contentTips = arrayOf(
        "Intenta reciclar y reutilizar productos tanto como sea posible. Evita los productos desechables que sólo se utilizan una vez y luego se tiran.",
        "Apaga las luces y los electrodomésticos cuando no los estés utilizando. Considera la posibilidad de utilizar bombillas de bajo consumo y aparatos con calificación energética A.",
        "Si es posible, camina, monta en bicicleta o utiliza el transporte público en lugar de conducir. Si tienes que conducir, intenta compartir el coche con otras personas.",
        "No dejes el agua corriendo mientras te cepillas los dientes o te afeitas. Intenta tomar duchas más cortas y considera la posibilidad de instalar grifos y duchas de bajo flujo.",
        "Esto no sólo ayuda a la economía local, sino que también reduce la cantidad de energía necesaria para transportar alimentos desde lejos.",
        "Los árboles absorben el dióxido de carbono, un gas de efecto invernadero. Plantar árboles puede ayudar a combatir el cambio climático y también proporciona un hábitat para muchas especies de animales.",
        "El embalaje adicional puede ser innecesario y es a menudo difícil de reciclar. Intenta comprar productos con el mínimo embalaje posible.",
        "Los residuos de alimentos y de jardín pueden ser compostados en casa, lo que reduce la cantidad de residuos que van al vertedero y proporciona un excelente fertilizante para tu jardín.",
        "Comparte tus conocimientos sobre el medio ambiente con amigos y familiares. Cuantas más personas sepan cómo cuidar el medio ambiente, más impacto podremos tener.",
        "Participar en o organizar eventos de limpieza en tu comunidad puede ayudar a mantener limpios los espacios públicos y a crear conciencia sobre la importancia de no tirar basura."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val tvNextRecolection = binding.tvNextRecolection

        val tvTitleConsejo = binding.tvTitleConsejo
        val tvConsejo = binding.textConsejo

        val randomTip = getRandomTip(titleTips)

        tvTitleConsejo.text = titleTips[randomTip]
        tvConsejo.text = contentTips[randomTip]

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
        date.set(year, month-1, day)
        val dayOfWeek = date.get(java.util.Calendar.DAY_OF_WEEK)
        return days[dayOfWeek-1]
    }

    private fun getRandomTip(titleTips : Array<String>) : Int{
        val random = (0..titleTips.size).random()
        if(random-1 == -1){
            return 0
        }
        return random-1
    }





}