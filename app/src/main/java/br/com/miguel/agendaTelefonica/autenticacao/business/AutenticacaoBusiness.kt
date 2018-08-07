package br.com.miguel.agendaTelefonica.autenticacao.business

import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.autenticacao.database.AutenticacaoDatabase
import br.com.miguel.agendaTelefonica.autenticacao.module.Usuario
import br.com.miguel.agendaTelefonica.autenticacao.network.AutenticacaoNetwork
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase

object AutenticacaoBusiness {

    fun entrar(usuario: Usuario, onSuccess: (usuario: Usuario) -> Unit, onError: (message: Int) -> Unit) {

        ContatoDatabase.apagarContatos()
        AutenticacaoNetwork.entrar(usuario, { usuario: Usuario ->
            usuario.let {
                AutenticacaoDatabase.salvarUsuario(it) {
                    onSuccess(it)
                }
            }
        }, {
            onError(R.string.erro_busca_usuario)
        })
    }

    fun sair(onSuccess: () -> Unit, onError: () -> Unit) {
        val usuario = AutenticacaoDatabase.getUsuario()

        usuario?.let {
            AutenticacaoNetwork.sair(usuario, {
                AutenticacaoDatabase.limparBanco()
                onSuccess()
            }, {
                onError()
            })
        }
    }

    fun criarUsuario(usuario: Usuario, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AutenticacaoNetwork.criarUsuario(usuario, {
            usuario.let {
                AutenticacaoDatabase.salvarUsuario(it) {
                    onSuccess()
                }
            }
        }, {
            onError(R.string.erro_criar_conta)
        })
    }

    fun isInputPreenchido(usuario: Usuario) = !usuario.email.isNullOrBlank() && !usuario.password.isNullOrBlank()
}