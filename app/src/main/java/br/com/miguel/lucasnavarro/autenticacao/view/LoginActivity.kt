package br.com.miguel.lucasnavarro.autenticacao.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.autenticacao.business.AutenticacaoBusiness
import br.com.miguel.lucasnavarro.autenticacao.database.AutenticacaoDatabase
import br.com.miguel.lucasnavarro.autenticacao.module.Usuario
import br.com.miguel.lucasnavarro.contato.view.ContatosActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Realm.init(this)

        val usuario = AutenticacaoDatabase.getUsuario()

        isLogado(usuario)

        cliqueEntrar()

        criarConta()
    }

    private fun isLogado(usuario: Usuario?) {
        usuario?.let {
            telaInicial()
        }
    }

    override fun onResume() {
        super.onResume()

        txtSenha.text = null
        txtEmail.text = null
    }

    fun cliqueEntrar() {

        btnEntrar.setOnClickListener {

            val usuario = criarUsuario()

            if (!usuario.email.isNullOrBlank() && !usuario.password.isNullOrBlank()) {
                entrar(usuario)
            } else {
                Snackbar.make(btnEntrar, R.string.preencha_campos, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun entrar(usuario: Usuario) {

        AutenticacaoBusiness.entrar(usuario ,{
            Snackbar.make(btnEntrar, R.string.msg_entrando, Snackbar.LENGTH_SHORT).show()
            telaInicial()
        }, { mensagemErroEntrar ->
            Snackbar.make(btnEntrar, mensagemErroEntrar, Snackbar.LENGTH_SHORT).show()
        })
    }

    fun criarConta() {

        btnCriarConta.setOnClickListener {
            val usuario = criarUsuario()

            AutenticacaoBusiness.criarUsuario(usuario ,{
                Snackbar.make(btnCriarConta, R.string.conta_criada, Snackbar.LENGTH_SHORT).show()
                AutenticacaoDatabase.limparBanco()
            }, { msgErroCriarConta ->
                Snackbar.make(btnCriarConta, msgErroCriarConta, Snackbar.LENGTH_SHORT).show()
            })
        }
    }

    private fun criarUsuario(): Usuario {

        val usuario = Usuario().apply {
            email = txtEmail.text.toString()
            password = txtSenha.text.toString()
            password_confirmation = txtSenha.text.toString()
        }
        return usuario
    }

    private fun telaInicial() {
        startActivity(Intent(this, ContatosActivity::class.java))
    }
}
