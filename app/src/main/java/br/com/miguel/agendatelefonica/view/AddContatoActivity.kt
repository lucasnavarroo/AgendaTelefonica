package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.activity_add_contato.*
import java.util.*

class AddContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contato)



//        onAddClick()
    }

    private fun onAddClick(id: String) {
        btnNovoContato.setOnClickListener {

            val usuario = AgendaDatabase.getUsuario(id)
            val contato = getContato()

            usuario?.let { usuario ->

                AgendaBusiness.criarContato(usuario, contato, {
                    AgendaDatabase.salvarContato(contato) {
                        Toast.makeText(this, "contato salvo com sucesso!",Toast.LENGTH_SHORT).show()
                    }
                }, {
                    R.string.erro_criar_contato
                })
            }
        }
    }

    private fun getContato(): Contato {
        val contato = Contato()

        contato.name = addNomeContato.text.toString()
        contato.birth = Date(addNiverContato.text.toString())
        contato.email = addEmailContato.text.toString()
        contato.phone = addNumeroContato.text.toString()
        contato.picture = addUrlImagemContato.text.toString()

        return contato
    }
}
