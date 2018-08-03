package br.com.miguel.agendatelefonica.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import br.com.miguel.agendatelefonica.R
import br.com.miguel.agendatelefonica.business.AgendaBusiness
import br.com.miguel.agendatelefonica.database.AgendaDatabase
import br.com.miguel.agendatelefonica.module.Contato
import br.com.miguel.agendatelefonica.module.Usuario
import br.com.miguel.agendatelefonica.view.AddContatoActivity
import br.com.miguel.agendatelefonica.view.DetalhesActivity
import br.com.miguel.agendatelefonica.view.EditarContatoActivity
import kotlinx.android.synthetic.main.contato_item.view.*

class ContatosAdapter(var contatos: List<Contato>, val context: Context, val usuario: Usuario) : RecyclerView.Adapter<ContatosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contato_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nome?.text = contatos.get(position).name
        holder.numero?.text = contatos.get(position).phone
        holder.email?.text = contatos.get(position).email
//        holder?.contato? = contatos.get(position)

        val id = contatos.get(position).id

        configClickDetalhesContato(holder, id)

        configLongClickApagarContato(holder, id, position)
    }

    private fun configLongClickApagarContato(holder: ViewHolder, id: Int?, position: Int) {
        holder.itemView.setOnLongClickListener {

            val popupMenu = PopupMenu(context, it)
            popupMenu.inflate(R.menu.item_menu_contato)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {

                    R.id.apagarContato -> {
                        if (id != null) {
                            AgendaBusiness.apagarContato(usuario, id, {
                                notifyDataSetChanged()
                                Toast.makeText(context, "contato apagado com sucesso", Toast.LENGTH_SHORT).show()
                            }, {
                                Toast.makeText(context, "erro ao apagar contato", Toast.LENGTH_SHORT).show()
                                Log.d("erroApagaContato", it)
                            })
                        }
                        true
                    }

                    R.id.editarContato -> {
                        if (id != null) {
                            val intent = Intent(context, EditarContatoActivity::class.java)
                            intent.putExtra("ID", id)
                            intent.putExtra("IdUsuario", usuario.id)
                            context.startActivity(intent)
                        }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
            false
        }
    }

    private fun configClickDetalhesContato(holder: ViewHolder, id: Int?) {
        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra("ID", id)

            context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome = view.txtNome
        val numero = view.textNumero
        val email = view.textEmail
        val contato = view.imageContato
    }
}