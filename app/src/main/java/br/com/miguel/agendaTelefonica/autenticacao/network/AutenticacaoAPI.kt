package br.com.miguel.agendaTelefonica.autenticacao.network

import br.com.miguel.agendaTelefonica.autenticacao.module.Data
import br.com.miguel.agendaTelefonica.autenticacao.module.Usuario
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AutenticacaoAPI {

    @POST("auth/sign_in")
    fun entrar(@Body usuario: Usuario): Observable<Response<Data>>

    @POST("/auth")
    fun criarUsuario(@Body usuario: Usuario): Observable<Data>

    @DELETE("auth/sign_out")
    fun sair(@Header("uid") uid: String?,
             @Header("client") client: String?,
             @Header("accessToken") accessToken: String?): Observable<Response<Data>>
}