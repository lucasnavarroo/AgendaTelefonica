package br.com.miguel.agendatelefonica.database

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

    fun limparContatos() {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.delete(Contato::class.java)
            realm.commitTransaction()
        }
    }

    fun apagarContato(id: Int) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.where(Contato::class.java).equalTo("id", id).findFirst()?.deleteFromRealm()
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

    fun salvarContatos(contatos: List<Contato>, onSuccess: () -> Unit) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealm(contatos)
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

    fun getContato(id: Int): Contato? {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            val contato: Contato? = realm.where(Contato::class.java).equalTo("id", id).findFirst()
            realm.commitTransaction()

            return realm.copyFromRealm(contato)
        }
    }
}