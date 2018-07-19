package br.com.miguel.agendatelefonica.network

import br.com.miguel.agendatelefonica.module.Data
import br.com.miguel.agendatelefonica.module.Usuario
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

interface AgendaAPI {

      @POST("auth/sign_in")
      fun entrar(@Body email: String, @Body senha: String): Observable<Data>

}