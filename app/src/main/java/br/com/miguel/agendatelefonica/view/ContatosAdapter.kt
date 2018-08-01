package br.com.miguel.agendatelefonica.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.module.Contato
import kotlinx.android.synthetic.main.contato_item.view.*

class ContatosAdapter(val contatos: ArrayList<Contato>, val context: Context) : RecyclerView.Adapter<ContatosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contato_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.nome?.text = contatos.get(position).name
        holder?.numero?.text = contatos.get(position).phone
        holder?.email?.text = contatos.get(position).email
//        holder?.contato? = contatos.get(position).
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome = view.txtNome
        val numero = view.textNumero
        val email = view.textEmail
        val contato = view.imageContato
    }
}