package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.activity_add_contato.*

class AddContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contato)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.extras.getInt("IdUsuario")

        onAddClick(id)
    }

    private fun onAddClick(id: Int) {
        btnNovoContato.setOnClickListener {

            val usuario = AgendaDatabase.getUsuario(id)
            val contato = getContato()

            usuario?.let { usuario ->

                AgendaBusiness.criarContato(usuario, contato, {
                    Snackbar.make(btnNovoContato, R.string.contato_salvo_com_sucesso, Snackbar.LENGTH_SHORT).show()
                    finish()
                }, {
                    Snackbar.make(btnNovoContato, R.string.erro_criar_contato, Snackbar.LENGTH_SHORT).show()
                })
            }
        }
    }

    private fun getContato(): Contato {
        val contato = Contato()

        contato.name = addNomeContato.text.toString()
        contato.birth = addNiverContato.text.toString().toInt()
        contato.email = addEmailContato.text.toString()
        contato.phone = addNumeroContato.text.toString()
        contato.picture = addUrlImagemContato.text.toString()

        return contato
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
