package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.activity_editar_contato.*

class EditarContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)

        val id: Int = intent.extras.getInt("ID")
        val idUsuario: Int = intent.extras.getInt("IdUsuario")

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
                AgendaBusiness.editarContato(usuario, id, {
                    Toast.makeText(this, "Usuario editado com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }, { mensagemErro ->
                    Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}
