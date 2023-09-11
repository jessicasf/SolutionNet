package net.solution

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.solution.databinding.PerguntaBinding

class Pergunta : AppCompatActivity() {

    private lateinit var binding: PerguntaBinding

    @SuppressLint("MutatingSharedPrefs")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PerguntaBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.btnEnviar.setOnClickListener(){
            if (binding.inputPergunta.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha a pergunta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (binding.inputPergunta.text.toString().length < 10) {
                Toast.makeText(this, "Preencha a pergunta com mais de 10 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("net.solution.prefs", MODE_PRIVATE)
            val perguntas = prefs.getStringSet("perguntas", mutableSetOf())?.toMutableSet()
            perguntas?.add(binding.inputPergunta.text.toString())
            prefs.edit().apply {
                putStringSet("perguntas", perguntas)
                apply()
            }

            Toast.makeText(this, "Pergunta enviada com sucesso!", Toast.LENGTH_SHORT).show()
            intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }
}