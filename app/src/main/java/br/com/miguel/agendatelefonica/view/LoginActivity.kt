package br.com.miguel.agendatelefonica.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.module.Usuario
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.contato_item.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun cliqueEntrar() {
        var usuario = Usuario()
        usuario.email = txtEmail.text.toString()
        usuario.senha = txtSenha.text.toString()

        btnEntrar.setOnClickListener {
            AgendaBusiness.entrar(usuario, {
                Snackbar.make(btnEntrar, "entrando...", Snackbar.LENGTH_SHORT).show()

            }, { message ->
                Snackbar.make(btnEntrar, message, Snackbar.LENGTH_SHORT).show()
            })
        }
    }

    fun criarConta() {
        btnCriarConta.setOnClickListener {
            var usuario = Usuario()
            usuario.email = txtEmail.text.toString()
            usuario.senha = txtSenha.text.toString()

            AgendaBusiness.criarConta(usuario, {
                Snackbar.make(btnCriarConta, "Conta criada", Snackbar.LENGTH_SHORT).show()
            }, { message ->
                Snackbar.make(btnCriarConta, message, Snackbar.LENGTH_SHORT).show()
            })
        }
    }

}
