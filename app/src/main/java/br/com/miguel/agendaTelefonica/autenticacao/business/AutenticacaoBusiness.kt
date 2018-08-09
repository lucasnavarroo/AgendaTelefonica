package br.com.miguel.agendaTelefonica.autenticacao.business

import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.autenticacao.database.AutenticacaoDatabase
import br.com.miguel.agendaTelefonica.autenticacao.module.Usuario
import br.com.miguel.agendaTelefonica.autenticacao.network.AutenticacaoNetwork
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase
import java.io.IOException

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

    fun criarUsuario(usuario: Usuario, onSuccess: () -> Unit, onError: (message: String?) -> Unit) {

        AutenticacaoNetwork.criarUsuario(usuario, {
            usuario.let {
                AutenticacaoDatabase.salvarUsuario(it) {
                    onSuccess()
                }
            }
        }, {
            onError(it)
        })
    }

    fun isInputPreenchido(usuario: Usuario) = !usuario.email.isNullOrBlank() && !usuario.password.isNullOrBlank()

    fun checkInternet(): Boolean {

        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0

        } catch (e: IOException) {
            e.printStackTrace()

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return false
    }
}