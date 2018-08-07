package br.com.miguel.lucasnavarro.contato.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.widget.Toast
import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.contato.business.ContatoBusiness
import br.com.miguel.lucasnavarro.contato.database.ContatoDatabase
import br.com.miguel.lucasnavarro.contato.module.Contato
import br.com.miguel.lucasnavarro.contato.view.ContatosActivity.Companion.ID_CONTATO
import kotlinx.android.synthetic.main.activity_editar_contato.*

class EditarContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.extras.getInt(ID_CONTATO)

        val contato = ContatoDatabase.getContato(id)

        contato?.apply {
            editNomeContato.setText(contato.name)
            editEmailContato.setText(contato.email)
            editUrlImagemContato.setText(contato.picture)
            editAniversarioContato.setText(contato.birth.toString())
            editNumeroContato.setText(contato.phone)
        }

        onEditClick(contato)
    }

    fun onEditClick(contato: Contato?) {

        btnAlterarContato.setOnClickListener {
            contato?.apply {
                name = editNomeContato.text.toString()
                email = editEmailContato.text.toString()
                picture = editUrlImagemContato.toString()
                birth = editAniversarioContato.text.toString().toInt()
                phone = editNumeroContato.text.toString()
            }

            ContatoBusiness.editarContato(contato?.id, contato, {
                Snackbar.make(btnAlterarContato, "Usuario editado com sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }, { mensagemErro ->
                Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
