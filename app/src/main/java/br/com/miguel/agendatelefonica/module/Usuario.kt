package br.com.miguel.agendatelefonica.module

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Usuario: RealmObject() {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("email")
    var email: String? = ""

    @SerializedName("password")
    var password: String? = ""

    @SerializedName("password_confirmation")
    var password_confirmation: String? = ""

    var provider: String? = null
    var uid: String? = null
    var allow_password_change: Boolean? = false
    var name: String? = ""
    var nickname: String? = ""
    var image: Int? = 0
    var client: String? = ""
    var accessToken: String? = ""
}