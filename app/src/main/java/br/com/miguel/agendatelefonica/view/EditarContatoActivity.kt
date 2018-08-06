package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import br.com.miguel.agendatelefonica.view.ContatosActivity.Companion.ID_CONTATO
import br.com.miguel.agendatelefonica.view.LoginActivity.Companion.ID_USUARIO
import kotlinx.android.synthetic.main.activity_editar_contato.*

class EditarContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.extras.getInt(ID_CONTATO)
        val idUsuario: Int = intent.extras.getInt(ID_USUARIO)

        val contato = AgendaDatabase.getContato(id)

        if (contato != null) {
            editNomeContato.setText(contato.name)
            editEmailContato.setText(contato.email)
            editUrlImagemContato.setText(contato.picture)
            editAniversarioContato.setText(contato.birth.toString())
            editNumeroContato.setText(contato.phone)
        }

        onEditClick(id, idUsuario)
    }

    fun onEditClick(id: Int, idUsuario: Int) {
        btnAlterarContato.setOnClickListener {
            val usuario = AgendaDatabase.getUsuario(idUsuario)
            val contato = Contato()

            contato.name = editNomeContato.text.toString()
            contato.email = editEmailContato.text.toString()
            contato.picture = editUrlImagemContato.toString()
            contato.birth = editAniversarioContato.text.toString().toInt()
            contato.phone = editNumeroContato.text.toString()

            if (usuario != null) {
                AgendaBusiness.editarContato(usuario, id, contato, {
                    Snackbar.make(btnAlterarContato, "Usuario editado com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }, { mensagemErro ->
                    Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
                })
            }
        }
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
