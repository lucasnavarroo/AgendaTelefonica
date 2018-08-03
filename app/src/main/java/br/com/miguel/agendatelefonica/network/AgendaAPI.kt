package br.com.miguel.agendatelefonica.network

import br.com.miguel.agendatelefonica.module.Contato
import br.com.miguel.agendatelefonica.module.Data
import br.com.miguel.agendatelefonica.module.Usuario
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface AgendaAPI {

    @POST("auth/sign_in")
    fun entrar(@Body usuario: Usuario): Observable<Response<Data>>

    @POST("/auth")
    fun criarUsuario(@Body usuario: Usuario): Observable<Data>

    @DELETE("auth/sign_out")
    fun sair(@Header("uid") uid: String,
             @Header("client") client: String,
             @Header("accessToken") accessToken: String): Observable<Data>

    @POST("/contacts")
    fun criarContato(@Header("uid") uid: String?,
                     @Header("client") client: String?,
                     @Header("access-token") accessToken: String?,
                     @Body contato: Contato): Observable<Contato>

    @GET("/contacts")
    fun listarContatos(@Header("uid") uid: String?,
                       @Header("client") client: String?,
                       @Header("access-token") accessToken: String?): Observable<List<Contato>>

    @DELETE("/contacts/{id}")
    fun apagarContato(@Header("uid") uid: String?,
                      @Header("client") client: String?,
                      @Header("access-token") accessToken: String?,
                      @Path("id") id: Int?): Observable<Response<Void>>

    @PUT("/contacts/{id}")
    fun editarContato(@Header("uid") uid: String?,
                      @Header("client") client: String?,
                      @Header("access-token") accessToken: String?,
                      @Path("id") id: String?): Observable<Contato>
}
