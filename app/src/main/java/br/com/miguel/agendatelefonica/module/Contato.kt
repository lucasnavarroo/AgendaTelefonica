package br.com.miguel.agendatelefonica.module

import io.realm.RealmObject
import java.util.*

open class Contato : RealmObject() {

    var name: String? = ""
    var birth: Int? = 0
    var email: String? = ""
    var phone: String? = ""
    var picture: String? = ""
}