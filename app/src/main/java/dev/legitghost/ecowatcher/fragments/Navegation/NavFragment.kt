package dev.legitghost.ecowatcher.fragments.Navegation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.legitghost.ecowatcher.R

class NavFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_nav, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navMenu);



        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBottomNav -> {
                    if(findNavController().currentDestination?.id != R.id.homeFragment) {
                        findNavController().navigate(R.id.action_addReminder_to_homeFragment)
                    }

                    true
                }
                R.id.reminderBottomNav -> {
                    if(findNavController().currentDestination?.id != R.id.addReminder) {
                        findNavController().navigate(R.id.action_homeFragment_to_addReminder)
                    }

                    true
                }
                else -> false
            }
        }

        return view;
    }

}