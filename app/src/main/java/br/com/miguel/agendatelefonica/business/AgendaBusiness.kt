package br.com.miguel.agendatelefonica.business

import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.network.AgendaNetwork

object AgendaBusiness {

    fun entrar(usuario: Usuario, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.entrar(usuario, { usuario: Usuario ->
            usuario.let {
                AgendaDatabase.salvarUsuario(it) {
                    onSuccess()
                }
            }
        }, {
            onError(R.string.erro_busca_usuario)
        })
    }

    fun criarUsuario(usuario: Usuario, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.criarUsuario(usuario, {
            onSuccess()
        }, {
            onError(R.string.erro_criar_conta)
        })
    }
}