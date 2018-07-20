package br.com.miguel.agendatelefonica.module

import io.realm.RealmObject
import java.util.*

open class Contato : RealmObject() {

    var id: Int? =  0
    var name: String? = ""
    var email: String? = ""
    var phone: String? = ""
    var picture: String? = ""
    var birth: Date? = null

}