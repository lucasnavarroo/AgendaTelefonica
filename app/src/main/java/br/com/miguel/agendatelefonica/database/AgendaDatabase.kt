package br.com.miguel.agendatelefonica.database

import android.content.Context
import br.com.miguel.agendatelefonica.module.Usuario
import io.realm.Realm

object AgendaDatabase {

    fun salvarUsuario(usuario: Usuario, onSuccess: () -> Unit) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealm(usuario)
            realm.commitTransaction()
            onSuccess()
        }
    }

    fun limparBanco() {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }
}