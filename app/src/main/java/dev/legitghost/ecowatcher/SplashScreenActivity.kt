package dev.legitghost.ecowatcher

import  android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.legitghost.ecowatcher.databinding.ActivitySplashScreenBinding
import java.util.ArrayList

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        val loadingText = binding.tvLoading

        setContentView(binding.root)

        binding.tvLogo.alpha = 0f
        binding.tvLogo.animate().setDuration(1000).alpha(1f).withEndAction {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        

    }
}