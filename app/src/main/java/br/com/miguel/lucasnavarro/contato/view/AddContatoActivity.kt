package br.com.miguel.lucasnavarro.contato.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.contato.business.ContatoBusiness
import br.com.miguel.lucasnavarro.contato.module.Contato
import kotlinx.android.synthetic.main.activity_add_contato.*

class AddContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contato)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        onAddClick()
    }

    private fun onAddClick() {

        btnNovoContato.setOnClickListener {
            val contato = getContato()

            ContatoBusiness.criarContato(contato, {
                Snackbar.make(btnNovoContato, R.string.contato_salvo_com_sucesso, Snackbar.LENGTH_SHORT).show()
                finish()
            }, {
                Snackbar.make(btnNovoContato, R.string.erro_criar_contato, Snackbar.LENGTH_SHORT).show()
            })

        }
    }

    private fun getContato(): Contato {

        return Contato().apply {
            name = addNomeContato.text.toString()
            birth = addNiverContato.text.toString().toInt()
            email = addEmailContato.text.toString()
            phone = addNumeroContato.text.toString()
            picture = addUrlImagemContato.text.toString()
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
