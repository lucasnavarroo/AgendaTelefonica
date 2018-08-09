package br.com.miguel.agendaTelefonica.autenticacao.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

open class Application : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this)
    }
}