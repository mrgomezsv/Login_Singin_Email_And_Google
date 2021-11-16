package com.login.app

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

    }

    private fun setup(email: String, provider: String) {
        val emailTextView : TextView = findViewById(R.id.emailTextView)
        val providerTextView : TextView = findViewById(R.id.providerTextView)
        val logOutButton : Button = findViewById(R.id.logOutButton)

        title = "Inicio"

        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {

            val prefs : SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_fire), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
            Toast.makeText(this, "Sesion Cerrada con Exito", Toast.LENGTH_LONG).show()
        }
    }
}