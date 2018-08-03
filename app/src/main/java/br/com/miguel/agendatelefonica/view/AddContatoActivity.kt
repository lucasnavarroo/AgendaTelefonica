package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.activity_add_contato.*

class AddContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contato)

        val id: Int = intent.extras.getInt("IdUsuario")

        onAddClick(id)
    }

    private fun onAddClick(id: Int) {
        btnNovoContato.setOnClickListener {

            val usuario = AgendaDatabase.getUsuario(id.toInt())
            val contato = getContato()

            usuario?.let { usuario ->

                AgendaBusiness.criarContato(usuario, contato, {
                    Toast.makeText(this, "contato salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }, {
                    Toast.makeText(this, R.string.erro_criar_contato, Toast.LENGTH_SHORT).show()
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
}
