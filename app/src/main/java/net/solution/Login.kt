package net.solution

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.solution.databinding.IndexBinding

class Login : AppCompatActivity() {

    private lateinit var binding: IndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IndexBinding.inflate(layoutInflater)
        setContentView(binding.root);

        val prefs = getSharedPreferences("net.solution.prefs", Context.MODE_PRIVATE)
        val nome = prefs.getString("nome", "")
        if (nome != "") {
            val intent = Intent(this, Home::class.java)
            Toast.makeText(this, "Olá novamente, ${nome}!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }


        binding.btnContinuar.setOnClickListener(){
            if (binding.inputNome.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (binding.inputNome.text.toString().length < 3) {
                Toast.makeText(this, "Preencha o nome com mais de 3 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            salvarDados()
        }

    }

    private fun salvarDados() {
        val prefs = getSharedPreferences("net.solution.prefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("nome", binding.inputNome.text.toString())
            apply()
        }
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

}