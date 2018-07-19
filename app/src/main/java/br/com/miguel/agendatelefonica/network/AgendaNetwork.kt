package br.com.miguel.agendatelefonica.network

import br.com.miguel.agendatelefonica.module.Data
import br.com.miguel.agendatelefonica.module.Usuario
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onError
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

    fun entrar(email: String, senha: String, onSucess: (data: Data) -> Unit, onError: () -> Unit) {

        agendaAPI.entrar(email, senha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ usuario ->

                    usuario?.let {
                        onSucess(it)
                    }

                }, {
                    onError()
                })

    }

}
