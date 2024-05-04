package dev.legitghost.ecowatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.legitghost.ecowatcher.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}