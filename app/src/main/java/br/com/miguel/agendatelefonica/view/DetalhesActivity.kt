package br.com.miguel.agendatelefonica.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val id: Int = intent.extras.getInt("ID")

        val contato = AgendaDatabase.getContato(id)

        contato?.let { contato ->
            txtDetalheNome.text = contato.name
            txtDetalheEmail.text = contato.email
            txtDetalheNumero.text = contato.phone
            txtDetalheBirth.text = contato.birth.toString()
        }
    }
}
