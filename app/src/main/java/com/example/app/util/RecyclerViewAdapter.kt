package com.example.app.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Products
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class RecyclerViewAdapter(
    private var products: MutableList<Products>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    var onItemClick: ((Products) -> Unit)? = null

    // Função que notifica a recyclerView com a lista de produtos pesquisados
    fun setFilteredList(products: MutableList<Products>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = products[position]
        holder.bind(produto)
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(produto)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(produto: Products) {
            val img: ImageView = itemView.findViewById(R.id.imgProduct)
            val txtProduto: TextView = itemView.findViewById(R.id.txtProduct)
            val txtFornecedor: TextView = itemView.findViewById(R.id.txtFornecedor)
            val txtValor: TextView = itemView.findViewById(R.id.txtPrice)

            val nf: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

            Picasso.get().load(produto.imagem).into(img)
            txtProduto.text = produto.nome
            txtFornecedor.text = produto.grupo
            txtValor.text = nf.format(produto.valorunitario).toString()
        }
    }

}