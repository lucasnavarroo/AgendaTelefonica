package br.com.miguel.lucasnavarro.autenticacao.module

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Usuario : RealmObject(), Serializable {

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("email")
    var email: String? = ""

    @SerializedName("password")
    var password: String? = ""

    @SerializedName("password_confirmation")
    var password_confirmation: String? = ""

    var provider: String? = ""
    var uid: String? = ""
    var allow_password_change: Boolean? = false
    var name: String? = ""
    var nickname: String? = ""
    var image: Int? = 0
    var client: String? = ""
    var accessToken: String? = ""
}