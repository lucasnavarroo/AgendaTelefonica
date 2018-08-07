package br.com.miguel.agendaTelefonica.contato.network

import br.com.miguel.agendaTelefonica.contato.module.Contato
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ContatoAPI {

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
                      @Body contato: Contato?,
                      @Path("id") id: String?): Observable<Contato>
}
