package br.com.miguel.agendatelefonica.business

import android.util.Log
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
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

    fun criarContato(usuario: Usuario, contato: Contato, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {
        AgendaNetwork.criarContato(usuario, contato, {
            contato.let {
                AgendaDatabase.salvarContato(it) {
                    onSuccess()
                }
            }
        }, {
            onError(R.string.erro_criar_contato)
            Log.d("erroCriarContato", it)
        })
    }

    fun listarContatos(usuario: Usuario, onSuccess: (contatos: List<Contato>) -> Unit, onError: (msg: String) -> Unit) {
        AgendaNetwork.listarContatos(usuario, { contatos ->
            AgendaDatabase.salvarContatos(
                    contatos
            ) {
                onSuccess(contatos)
            }
        }, {
            onError("erro ao listar contatos")
            Log.d("erroListarContatos", it)
        })
    }

    fun apagarContato(usuario: Usuario, id: Int, onSuccess: () -> Unit, onError: (msg: String) -> Unit) {
        AgendaNetwork.apagarContato(usuario, id, {
            AgendaDatabase.apagarContato(id)
            onSuccess()
        }, {
            onError("erro ao apagar contato")
        })
    }
}