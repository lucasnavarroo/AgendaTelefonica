package br.com.miguel.agendatelefonica.module

import io.realm.RealmObject

open class Usuario: RealmObject() {

    var id: String? = ""
    var email: String? = ""
    var senha: String? = ""
    var provider: String? = ""
    var uid: String? = ""
    var allow_password_change: Boolean = false
    var name: String? = ""
    var nickname: String? = ""
    var image: Int? = 0
    var client: String? = ""
    var accessToken: String? = ""
}