package br.com.miguel.agendatelefonica.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.view.LoginActivity.Companion.ID_USUARIO
import br.com.miguel.agendatelefonica.view.adapter.ContatosAdapter
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {
    private var usuario: Usuario? = null

    companion object {
        val ID_CONTATO: String? = "ID_CONTATO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: Int = intent.extras.getInt(ID_USUARIO)
        usuario = AgendaDatabase.getUsuario(id)

        listarContatos(usuario)

        onAddClick(id)
    }

    override fun onResume() {
        super.onResume()

        listarContatos(usuario)

        swipeRefresh.setOnRefreshListener {
            listarContatos(usuario)
        }
    }

    private fun listarContatos(usuario: Usuario?) {
        usuario?.let { usuarioResponse ->
            swipeRefresh.isRefreshing = true
            AgendaBusiness.listarContatos(usuarioResponse, {
                lista_contatos.layoutManager = LinearLayoutManager(this)
                lista_contatos.adapter = ContatosAdapter(AgendaDatabase.getContatos(), this, usuarioResponse)
                swipeRefresh.isRefreshing = false
            }, {
                Snackbar.make(lista_contatos, R.string.erro_listar_contatos, Snackbar.LENGTH_SHORT).show()
                swipeRefresh.isRefreshing = false
            })
        }
    }

    private fun onAddClick(id: Int) {

        btnNovoContato.setOnClickListener {
            val intent = Intent(this, AddContatoActivity::class.java)
            intent.putExtra(ID_USUARIO, id)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> {
                AgendaBusiness.sair(usuario!!)
                finishAffinity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
