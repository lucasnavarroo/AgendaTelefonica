package br.com.miguel.agendaTelefonica.autenticacao.network

import br.com.miguel.agendaTelefonica.autenticacao.module.Usuario
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object AutenticacaoNetwork {

    val autenticacaoAPI by lazy {
        getRetrofit().create(AutenticacaoAPI::class.java)
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api-agenda-unifor.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun entrar(usuario: Usuario, onSucess: (usuario: Usuario) -> Unit, onError: () -> Unit) {

        autenticacaoAPI.entrar(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    val user = response.body()?.data

                    user?.let {

                        it.accessToken = response.headers()["access-token"]
                        it.uid = response.headers()["uid"]
                        it.client = response.headers()["client"]
                        onSucess(it)
                    }
                }, {
                    onError()
                })
    }

    fun criarUsuario(usuario: Usuario, onSucess: (usuario: Usuario) -> Unit, onError: (msg: String?) -> Unit) {

        autenticacaoAPI.criarUsuario(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    val usuario = response.data

                    usuario?.let { onSucess(it) }
                }, {
                    onError(it.message)
                })
    }


    fun sair(usuario: Usuario, onSuccess: () -> Unit, onError: () -> Unit) {

        autenticacaoAPI.sair(usuario.uid, usuario.client, usuario.accessToken )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()
                }, {
                    onError()
                })
    }
}