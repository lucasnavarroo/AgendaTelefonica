package br.com.miguel.agendaTelefonica.contato.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.autenticacao.view.Application
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase
import br.com.miguel.agendaTelefonica.contato.module.Contato
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity.Companion.ID_CONTATO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*
import java.text.SimpleDateFormat
import java.util.*

class DetalhesActivity : Application() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.extras.getInt(ID_CONTATO)

        val contato = ContatoDatabase.getContato(id)

        preencherDadosContato(contato)
    }

    private fun preencherDadosContato(contato: Contato?) {

        contato?.let { contato ->
            txtDetalheNome.text = contato.name
            txtDetalheEmail.text = contato.email
            txtDetalheNumero.text = contato.phone

            val birth = getDate(contato.birth)
            txtDetalheBirth.text = birth

            Picasso.get().load(contato.picture.toString()).placeholder(R.drawable.ic_person_black_24dp).fit().into(imgDetalheContato)
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

    private fun getDate(timestamp: Long?): String {

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val data = Date(timestamp.toString().toLong())

        return dateFormat.format(data)
    }
}
