package dev.legitghost.ecowatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.legitghost.ecowatcher.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}