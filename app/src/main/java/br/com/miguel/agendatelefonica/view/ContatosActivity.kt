package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.miguel.agendatelefonica.R
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: String = intent.extras.getString("ID")

        textView3.text = id
    }
}
