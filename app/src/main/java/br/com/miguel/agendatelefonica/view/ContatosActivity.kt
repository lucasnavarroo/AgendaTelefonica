package br.com.miguel.agendatelefonica.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.miguel.agendatelefonica.R
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: String = intent.extras.getString("ID")

        lista_contatos.layoutManager = LinearLayoutManager(this)
//        lista_contatos.adapter = ContatosAdapter(listaContatos, this)

        btnNovoContato.setOnClickListener {
            val intent = Intent(this, AddContatoActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }
    }
}
