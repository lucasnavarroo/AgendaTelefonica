package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.module.Usuario
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cliqueEntrar()

        criarConta()
    }

    fun cliqueEntrar() {

        btnEntrar.setOnClickListener {

            var usuario = Usuario()
            usuario.email = txtEmail.text.toString()
            usuario.senha = txtSenha.text.toString()

            Log.d("usuario", usuario.email + " ," + usuario.senha)

            AgendaBusiness.entrar(usuario, {
                Snackbar.make(btnEntrar, R.string.msg_entrando, Snackbar.LENGTH_SHORT).show()
                Toast.makeText(this, "entrando...", Toast.LENGTH_SHORT).show()

            }, { message ->
                Snackbar.make(btnEntrar, message, Snackbar.LENGTH_SHORT).show()

            })
        }
    }

    fun criarConta() {

        btnCriarConta.setOnClickListener {

            val usuario = Usuario()
            usuario.email = txtEmail.text.toString()
            usuario.senha = txtSenha.text.toString()

            AgendaBusiness.criarUsuario(usuario, {
                Snackbar.make(btnCriarConta, "Conta criada", Snackbar.LENGTH_SHORT).show()
            }, { message ->
                Snackbar.make(btnCriarConta, message, Snackbar.LENGTH_SHORT).show()
            })
        }
    }
}
