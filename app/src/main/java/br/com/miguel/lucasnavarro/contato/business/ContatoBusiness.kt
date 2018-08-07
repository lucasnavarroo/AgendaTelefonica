package br.com.miguel.lucasnavarro.contato.business

import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.autenticacao.database.AutenticacaoDatabase
import br.com.miguel.lucasnavarro.contato.database.ContatoDatabase
import br.com.miguel.lucasnavarro.contato.module.Contato
import br.com.miguel.lucasnavarro.contato.network.ContatoNetwork

object ContatoBusiness {

    fun criarContato(contato: Contato, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        val usuario = AutenticacaoDatabase.getUsuario()

        usuario?.let {
            ContatoNetwork.criarContato(usuario, contato, {
                contato.let {
                    ContatoDatabase.salvarContato(it) {
                        onSuccess()
                    }
                }
            }, {
                onError(R.string.erro_criar_contato)
            })
        }
    }

    fun listarContatos(onSuccess: () -> Unit, onError: (msg: String) -> Unit) {

        val usuario = AutenticacaoDatabase.getUsuario()

        usuario?.let {

            ContatoDatabase.apagarContatos()
            ContatoNetwork.listarContatos(usuario, { contatos ->
                ContatoDatabase.salvarContatos(contatos) {
                    onSuccess()
                }
            }, {
                onError("erro ao listar contatos")
            })
        }
    }

    fun apagarContato(id: Int, onSuccess: () -> Unit, onError: (msg: String) -> Unit) {

        val usuario = AutenticacaoDatabase.getUsuario()

        usuario?.let {

            ContatoNetwork.apagarContato(usuario, id, {
                ContatoDatabase.apagarContato(id)
                onSuccess()
            }, {
                onError("erro ao apagar contato")
            })
        }
    }

    fun editarContato(id: Int?, contato: Contato?, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        val usuario = AutenticacaoDatabase.getUsuario()

        usuario?.let {

            ContatoNetwork.editarContato(usuario, contato, id, { contato ->
                ContatoDatabase.editarContato(contato) {
                    onSuccess()
                }
            }, {
                onError(R.string.erro_editar_contato)
            })
        }
    }
}