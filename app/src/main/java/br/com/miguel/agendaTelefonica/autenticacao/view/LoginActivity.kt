package br.com.miguel.agendaTelefonica.autenticacao.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.autenticacao.business.AutenticacaoBusiness
import br.com.miguel.agendaTelefonica.autenticacao.database.AutenticacaoDatabase
import br.com.miguel.agendaTelefonica.autenticacao.module.Usuario
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Application() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usuario = AutenticacaoDatabase.getUsuario()

        isLogado(usuario)

        cliqueEntrar()

        cliqueCriarConta()
    }

    override fun onResume() {
        super.onResume()

        txtSenha.text = null
        txtEmail.text = null
    }

    private fun isLogado(usuario: Usuario?) {
        usuario?.let {
            telaInicial()
        }
    }

    fun cliqueEntrar() {
        btnEntrar.setOnClickListener {

            val usuario = criarUsuario()

            if (!AutenticacaoBusiness.checkInternet()) {

                Snackbar.make(btnCriarConta, R.string.verifique_conexao, Snackbar.LENGTH_SHORT).show()

            } else if (!AutenticacaoBusiness.isInputPreenchido(usuario)) {

                Snackbar.make(btnCriarConta, R.string.preencha_campos, Snackbar.LENGTH_SHORT).show()

            } else entrar(usuario)
        }
    }

    private fun entrar(usuario: Usuario) {

        AutenticacaoBusiness.entrar(usuario, {
            Snackbar.make(btnEntrar, R.string.msg_entrando, Snackbar.LENGTH_SHORT).show()
            telaInicial()
        }, { mensagemErroEntrar ->
            Snackbar.make(btnEntrar, mensagemErroEntrar, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun cliqueCriarConta() {

        btnCriarConta.setOnClickListener {
            val usuario = criarUsuario()

            if (!AutenticacaoBusiness.checkInternet()) {

                Snackbar.make(btnCriarConta, R.string.verifique_conexao, Snackbar.LENGTH_SHORT).show()

            } else if (!AutenticacaoBusiness.isInputPreenchido(usuario)) {

                Snackbar.make(btnCriarConta, R.string.preencha_campos, Snackbar.LENGTH_SHORT).show()

            } else criarConta(usuario)
        }
    }

    fun criarConta(usuario: Usuario) {

        AutenticacaoBusiness.criarUsuario(usuario, {
            Snackbar.make(btnCriarConta, R.string.conta_criada, Snackbar.LENGTH_SHORT).show()
            AutenticacaoDatabase.limparBanco()
        }, { msgErroCriarConta ->
            Snackbar.make(btnCriarConta, R.string.erro_criar_conta, Snackbar.LENGTH_SHORT).show()
            Log.d("erroCriarConta", msgErroCriarConta)
        })
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
