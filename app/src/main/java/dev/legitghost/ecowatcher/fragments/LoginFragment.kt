package dev.legitghost.ecowatcher.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dev.legitghost.ecowatcher.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById<Button>(R.id.btnAcceder).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
        }

        return view
    }


    private fun inputCheck(name: String, email: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(age))
    }

}