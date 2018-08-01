package br.com.miguel.agendatelefonica.database

import android.content.Context
import br.com.miguel.agendatelefonica.module.Contato
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

    fun salvarContato(contato: Contato, onSuccess: () -> Unit) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealm(contato)
            realm.commitTransaction()
            onSuccess()
        }
    }

    fun getUsuario(id: String): Usuario? {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            val usuario: Usuario? = realm.where(Usuario::class.java).equalTo("id", id).findFirst()
            realm.commitTransaction()

            return realm.copyFromRealm(usuario)
        }
    }
}