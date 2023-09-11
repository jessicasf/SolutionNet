package net.solution

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaPerguntas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_perguntas)

        val listView: ListView = findViewById(R.id.listView)

        val prefs = getSharedPreferences("net.solution.prefs", Context.MODE_PRIVATE)
        val perguntas = prefs.getStringSet("perguntas", setOf<String>())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, perguntas?.toTypedArray() ?: arrayOf<String>())

        listView.adapter = adapter
    }
}
