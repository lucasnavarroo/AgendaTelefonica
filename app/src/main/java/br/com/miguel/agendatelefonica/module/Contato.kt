package br.com.miguel.agendatelefonica.module

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class Contato : RealmObject(), Serializable {

    @PrimaryKey
    var id: Int? = 0

    var name: String? = ""
    var birth: Int? = 0
    var email: String? = ""
    var phone: String? = ""
    var picture: String? = ""
}