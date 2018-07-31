package br.com.miguel.agendatelefonica.network

import android.util.Log
import br.com.miguel.agendatelefonica.module.Contato
import br.com.miguel.agendatelefonica.module.Usuario
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AgendaNetwork {

    val agendaAPI by lazy {
        getRetrofit().create(AgendaAPI::class.java)
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api-agenda-unifor.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun entrar(usuario: Usuario, onSucess: (usuario: Usuario) -> Unit, onError: () -> Unit) {

        agendaAPI.entrar(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseUser ->

                    val user = responseUser.body()

                    user?.let {

                        it.accessToken = responseUser.headers()["access-token"]
                        it.uid = responseUser.headers()["uid"]
                        it.client = responseUser.headers()["client"]
                        it.id = user.id
                        onSucess(it)
                    }

                }, {
                    onError()
                    Log.d("erroLogin", it.message.toString())
                })
    }

    fun criarUsuario(usuario: Usuario, onSucess: () -> Unit, onError: () -> Unit) {

        agendaAPI.criarUsuario(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->

                    val usuario =  data

                    onSucess()
                }, {
                    onError()
                    Log.d("erroCriarConta", it.message.toString())
                })
    }

    fun sair(usuario: Usuario, onSuccess: () -> Unit, onError: () -> Unit) {

        var uid: String = ""
        var accessToken: String = ""
        var client: String = ""

        usuario.uid?.let {
            uid = it
        }
        usuario.accessToken?.let {
            accessToken = it
        }
        usuario.client?.let {
            client = it
        }

        agendaAPI.sair(uid, client, accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()
                }, {
                    onError()
                })
    }

    fun criarContato(contato: Contato, onSuccess: () -> Unit, onError: () -> Unit) {

    }
}
