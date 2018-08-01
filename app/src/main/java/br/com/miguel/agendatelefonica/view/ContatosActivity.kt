package br.com.miguel.agendatelefonica.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.activity_contatos.*

class ContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        val id: String = intent.extras.getString("ID")

        val listaContatos: ArrayList<Contato> = ArrayList()

        addList(listaContatos)

        Log.d("tamanhoLista", listaContatos.size.toString())

        lista_contatos.layoutManager = LinearLayoutManager(this)
        lista_contatos.adapter = ContatosAdapter(listaContatos, this)
    }

    fun addList(listaContatos: ArrayList<Contato>) {

        val contato1 = Contato()
        val contato2 = Contato()
        val contato3 = Contato()

        contato1.name = "joao"
        contato1.phone = "85996654822"
        contato1.email = "lucasnavarro878@gmail.com"

        contato2.name = "maria"
        contato2.phone = "857887788"
        contato2.email = "maria@maria.maria"

        contato3.name = "fulano"
        contato3.phone = "85456456"
        contato3.email = "fulano@fulano.com"

        listaContatos.add(0, contato2)
        listaContatos.add(1, contato1)
        listaContatos.add(2, contato3)
    }
}
