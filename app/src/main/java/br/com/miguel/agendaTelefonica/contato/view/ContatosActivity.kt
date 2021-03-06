package br.com.miguel.agendaTelefonica.contato.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.autenticacao.business.AutenticacaoBusiness
import br.com.miguel.agendaTelefonica.autenticacao.view.Application
import br.com.miguel.agendaTelefonica.contato.business.ContatoBusiness
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase
import br.com.miguel.agendaTelefonica.contato.adapter.ContatoAdapter
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : Application() {

    companion object {
        val ID_CONTATO: String? = "ID_CONTATO"
        val IS_EDIT: String = "IS_EDIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        listarContatos()

        onAddClick()
    }

    override fun onResume() {
        super.onResume()

        listarContatos()

        swipeRefresh.setOnRefreshListener {
            listarContatos()
        }
    }

    private fun listarContatos() {

        swipeRefresh.isRefreshing = true
        ContatoBusiness.listarContatos({
            setupRecyclerView()
            swipeRefresh.isRefreshing = false
        }, {
            Snackbar.make(listaContatos, R.string.erro_listar_contatos, Snackbar.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        })
    }

    private fun setupRecyclerView() {
        listaContatos.layoutManager = LinearLayoutManager(this)
        listaContatos.adapter = ContatoAdapter(ContatoDatabase.getContatos(), this)
    }

    private fun onAddClick() {
        btnNovoContato.setOnClickListener {
            val intent = Intent(this, NovoContatoActivity::class.java)
            intent.putExtra(IS_EDIT, false)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                AutenticacaoBusiness.sair({
                    finish()
                }, {
                    Snackbar.make(listaContatos, R.string.erro_sair, Snackbar.LENGTH_SHORT).show()
                })
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
