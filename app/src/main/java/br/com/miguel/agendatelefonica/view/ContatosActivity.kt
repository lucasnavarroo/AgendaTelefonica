package br.com.miguel.agendatelefonica.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.view.adapter.ContatosAdapter
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: Int = intent.extras.getInt("IdUsuario")
        val usuario = AgendaDatabase.getUsuario(id.toInt())

        onAddClick(id)

        listarContatos(usuario)
    }

    override fun onDestroy() {
        super.onDestroy()
        AgendaDatabase.limparBanco()
    }

    private fun listarContatos(usuario: Usuario?) {
        usuario?.let { usuario ->
            AgendaBusiness.listarContatos(usuario, { listaContatos ->

                lista_contatos.layoutManager = LinearLayoutManager(this)
                lista_contatos.adapter = ContatosAdapter(listaContatos, this, usuario)
            }, {
                Toast.makeText(this, "erro ao listar contatos", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun onAddClick(id: Int) {
        btnNovoContato.setOnClickListener {
            val intent = Intent(this, AddContatoActivity::class.java)
            intent.putExtra("IdUsuario", id)
            startActivity(intent)
        }
    }
}
