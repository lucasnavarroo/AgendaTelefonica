package br.com.miguel.lucasnavarro.contato.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import br.com.miguel.lucasnavarro.R
import br.com.miguel.lucasnavarro.contato.business.ContatoBusiness
import br.com.miguel.lucasnavarro.contato.database.ContatoDatabase
import br.com.miguel.lucasnavarro.contato.module.Contato
import br.com.miguel.lucasnavarro.contato.view.ContatosActivity.Companion.ID_CONTATO
import br.com.miguel.lucasnavarro.contato.view.DetalhesActivity
import br.com.miguel.lucasnavarro.contato.view.EditarContatoActivity
import br.com.miguel.lucasnavarro.contato.view.item.ContatoItemView

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
            intent.putExtra("ID_CONTATO", id)
            context.startActivity(intent)
        }
    }

    private fun onLongClick(holder: ContatoItemView, id: Int?) {
        holder.itemView.setOnLongClickListener { itemView ->

            val popupMenu = PopupMenu(context, itemView)
            popupMenu.inflate(R.menu.item_menu_contato)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.apagarContato -> apagarContato(id)
                    R.id.editarContato -> editarContato(id)
                    else -> false
                }
            }
            popupMenu.show()
            true
        }
    }

    private fun editarContato(id: Int?): Boolean {

        if (id != null) {
            val intent = Intent(context, EditarContatoActivity::class.java)
            intent.putExtra(ID_CONTATO, id)
            context.startActivity(intent)
        }
        return true
    }

    private fun apagarContato(id: Int?): Boolean {

        id?.let {
            ContatoBusiness.apagarContato(id, {
                refreshContatos()
                Toast.makeText(context, R.string.erro_apagar_contato, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context, R.string.erro_apagar_contato, Toast.LENGTH_SHORT).show()
            })
        }
        return true
    }

    fun refreshContatos() {
        this.contatos = ContatoDatabase.getContatos()
        notifyDataSetChanged()
    }
}