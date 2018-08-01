package br.com.miguel.agendatelefonica.network

import br.com.miguel.agendatelefonica.module.Contato
import br.com.miguel.agendatelefonica.module.Data
import br.com.miguel.agendatelefonica.module.Usuario
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header

interface AgendaAPI {

    @POST("auth/sign_in")
    fun entrar(@Body usuario: Usuario): Observable<Response<Data>>

    @POST("/auth")
    fun criarUsuario(@Body usuario: Usuario): Observable<Data>

    @DELETE("auth/sign_out")
    fun sair(@Header("uid") uid: String, @Header("client") client: String, @Header("accessToken") accessToken: String): Observable<Data>

    @POST("/contacts")
    fun criarContato(@Body contato: Contato, @Header("uid") uid: String?, @Header("client") client: String?, @Header("acecessToken") accessToken: String?): Observable<Contato>
}
