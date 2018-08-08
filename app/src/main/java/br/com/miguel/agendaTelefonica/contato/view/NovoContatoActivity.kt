package br.com.miguel.agendaTelefonica.contato.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.widget.Toast
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.contato.business.ContatoBusiness
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase
import br.com.miguel.agendaTelefonica.contato.module.Contato
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity.Companion.ID_CONTATO
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity.Companion.IS_EDIT
import kotlinx.android.synthetic.main.activity_novo_contato.*

class NovoContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_contato)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val isEdit = intent.extras.getBoolean(IS_EDIT)

        if (isEdit) {
            val id: Int = intent.extras.getInt(ID_CONTATO)
            getContatoById(id)?.let { onEditClick(it) }
        } else onAddClick()
    }

    private fun onAddClick() {

        btnNovoContato.setOnClickListener {
            val contato = Contato().apply { preencherContato() }

            if (ContatoBusiness.isInputPreenchido((contato))) {
                ContatoBusiness.criarContato(contato, {
                    Toast.makeText(this, R.string.sucesso_salvar_contato, Toast.LENGTH_SHORT).show()
                    finish()
                }, {
                    Toast.makeText(this, R.string.erro_salvar_contato, Toast.LENGTH_SHORT).show()
                })
            } else {
                Snackbar.make(btnNovoContato, R.string.preencha_campos, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onEditClick(contato: Contato) {

        btnNovoContato.setOnClickListener {

            if (ContatoBusiness.isInputPreenchido(contato)) {
                contato.apply { preencherContato() }

                ContatoBusiness.editarContato(contato.id, contato, {
                    Snackbar.make(btnNovoContato, R.string.succeso_editar_contato, Toast.LENGTH_SHORT).show()
                    finish()
                }, { mensagemErro ->
                    Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
                })
            } else {
                Snackbar.make(btnNovoContato, R.string.preencha_campos, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Contato.preencherContato() {

        name = editNomeContato.text.toString()
        email = editEmailContato.text.toString()
        picture = editUrlImagemContato.toString()

        birth = if (editAniversarioContato.text.toString().isBlank()) {
            0
        } else {
            editAniversarioContato.text.toString().toInt()
        }

        phone = editNumeroContato.text.toString()
    }

    private fun getContatoById(id: Int): Contato? {

        val contato = ContatoDatabase.getContato(id)
        return contato?.apply { preencherTelaInput(contato) }
    }

    private fun preencherTelaInput(contato: Contato) {
        editNomeContato.setText(contato.name)
        editEmailContato.setText(contato.email)
        editUrlImagemContato.setText(contato.picture)
        editAniversarioContato.setText(contato.birth.toString())
        editNumeroContato.setText(contato.phone)
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
