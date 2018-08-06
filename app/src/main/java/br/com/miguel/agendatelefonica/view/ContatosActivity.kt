package br.com.miguel.agendatelefonica.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.view.adapter.ContatosAdapter
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: Int = intent.extras.getInt("IdUsuario")
        usuario = AgendaDatabase.getUsuario(id)

        listarContatos(usuario)

        onAddClick(id)
    }

    override fun onResume() {
        super.onResume()

        listarContatos(usuario)
    }

    private fun listarContatos(usuario: Usuario?) {
        usuario?.let { usuarioResponse ->
            AgendaBusiness.listarContatos(usuarioResponse, {
                lista_contatos.layoutManager = LinearLayoutManager(this)
                lista_contatos.adapter = ContatosAdapter(AgendaDatabase.getContatos(), this, usuarioResponse)
            }, {
                Snackbar.make(lista_contatos, R.string.erro_listar_contatos, Snackbar.LENGTH_SHORT).show()
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
