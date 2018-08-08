package br.com.miguel.agendaTelefonica.contato.adapter

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import br.com.miguel.agendaTelefonica.R
import br.com.miguel.agendaTelefonica.contato.business.ContatoBusiness
import br.com.miguel.agendaTelefonica.contato.database.ContatoDatabase
import br.com.miguel.agendaTelefonica.contato.module.Contato
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity.Companion.ID_CONTATO
import br.com.miguel.agendaTelefonica.contato.view.ContatosActivity.Companion.IS_EDIT
import br.com.miguel.agendaTelefonica.contato.view.DetalhesActivity
import br.com.miguel.agendaTelefonica.contato.view.NovoContatoActivity
import br.com.miguel.agendaTelefonica.contato.view.item.ContatoItemView

class ContatoAdapter(var contatos: List<Contato>, val context: Context) : RecyclerView.Adapter<ContatoItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoItemView {
        return ContatoItemView(LayoutInflater.from(context).inflate(R.layout.contato_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(contatoItemView: ContatoItemView, postion: Int) {

        val contato: Contato = contatos[postion]
        val id = contato.id

        contatoItemView.bind(contato)

        onClick(contatoItemView, id)

        onLongClick(contatoItemView, id)
    }

    private fun onClick(holder: ContatoItemView, id: Int?) {
        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra(ID_CONTATO, id)
            context.startActivity(intent)
        }
    }

    private fun onLongClick(holder: ContatoItemView, id: Int?) {
        holder.itemView.setOnLongClickListener { itemView ->

            val popupMenu = PopupMenu(context, itemView)
            popupMenu.inflate(R.menu.item_menu_contato)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.apagarContato -> apagarContato(id, holder.itemView)
                    R.id.editarContato -> editarContato(id, holder.itemView)
                    else -> false
                }
            }
            popupMenu.show()
            true
        }
    }

    private fun editarContato(id: Int?, view: View?): Boolean {

        if (id != null) {
            val intent = Intent(context, NovoContatoActivity::class.java)
            intent.putExtra(ID_CONTATO, id)
            intent.putExtra(IS_EDIT, true)
            context.startActivity(intent)
        }
        return true
    }

    private fun apagarContato(id: Int?, view: View): Boolean {

        id?.let {
            ContatoBusiness.apagarContato(id, {
                refreshContatos()
                Snackbar.make(view,R.string.sucesso_apagar_contato,Snackbar.LENGTH_SHORT).show()
            }, {
                Snackbar.make(view,R.string.erro_apagar_contato,Snackbar.LENGTH_SHORT).show()
            })
        }
        return true
    }

    fun refreshContatos() {
        this.contatos = ContatoDatabase.getContatos()
        notifyDataSetChanged()
    }
}