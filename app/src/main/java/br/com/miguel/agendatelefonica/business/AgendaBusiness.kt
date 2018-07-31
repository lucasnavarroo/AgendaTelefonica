package br.com.miguel.agendatelefonica.business

import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.network.AgendaNetwork

object AgendaBusiness {

    fun entrar(usuario: Usuario, onSuccess: (usuario: Usuario) -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.entrar(usuario, { usuario: Usuario ->
            usuario.let {
                AgendaDatabase.salvarUsuario(it) {
                    onSuccess(it)
                }
            }
        }, {
            onError(R.string.erro_busca_usuario)
        })
    }

    fun criarUsuario(usuario: Usuario, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.criarUsuario(usuario, {
            usuario.let {
                AgendaDatabase.salvarUsuario(it) {
                    onSuccess()
                }
            }
        }, {
            onError(R.string.erro_criar_conta)
        })
    }

    fun sair(usuario: Usuario, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.sair(usuario, {
            AgendaDatabase.limparBanco()
        }, {
            onError(R.string.logout_error)
        })
    }

//    fun criarContato(contato: Contato, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {
//        AgendaNetwork.criarContato(contato, {
//            contato.let {
//                AgendaDatabase.salvarContato(it) {
//                    onSuccess()
//                }
//            }
//
//        }, {
//            onError
//        })
//    }
}