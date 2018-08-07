package br.com.miguel.agendaTelefonica.contato.view.item

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.contato.module.Contato
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.contato_item.view.*

class ContatoItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(contato: Contato) {
        itemView.apply {
            txtNome.text = contato.name
            textNumero.text = contato.phone
            textEmail.text = contato.email
            Picasso.get().load(contato.picture).placeholder(R.drawable.ic_person_black_24dp).fit().into(imageContato)
        }
    }
}