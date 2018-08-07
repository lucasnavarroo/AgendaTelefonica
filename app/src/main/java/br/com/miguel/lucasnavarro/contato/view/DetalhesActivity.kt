package br.com.miguel.lucasnavarro.contato.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.contato.database.ContatoDatabase
import br.com.miguel.lucasnavarro.contato.view.ContatosActivity.Companion.ID_CONTATO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.extras.getInt(ID_CONTATO)

        val contato = ContatoDatabase.getContato(id)

        contato?.let { contato ->
            txtDetalheNome.text = contato.name
            txtDetalheEmail.text = contato.email
            txtDetalheNumero.text = contato.phone
            txtDetalheBirth.text = contato.birth.toString()
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
}
