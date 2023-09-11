package net.solution

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.solution.databinding.HomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: HomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root);
        
        try {
            val user = recuperarDados()
        }
        catch (e: Exception) {
            startActivity(Intent(this, Login::class.java))
        }

        binding.btnNovaPergunta.setOnClickListener(){
            val intent = Intent(this, Pergunta::class.java)
            startActivity(intent)
        }

        binding.btnListaPergunta.setOnClickListener(){
            val intent = Intent(this, ListaPerguntas::class.java)
            startActivity(intent)
        }

        binding.btnSaida.setOnClickListener(){
            val prefs = deleteSharedPreferences("net.solution.prefs")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    private fun recuperarDados(): String? {
        val prefs = getSharedPreferences("net.solution.prefs", Context.MODE_PRIVATE)
        val nome = prefs.getString("nome", "valor se nulo")
        binding.textoEntrada.text = "Olá, $nome"
        return nome;
    }



}