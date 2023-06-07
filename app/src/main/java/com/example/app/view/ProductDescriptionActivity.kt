package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.app.R
import com.example.app.databinding.ActivityProductDescriptionBinding
import com.example.app.model.Products
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale

class ProductDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.productDescriptionToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val produto = intent.getParcelableExtra<Products>("products")
        if (produto != null) {
            val txtDesc: TextView = binding.txtDesc
            val imgDesc: ImageView = binding.imgDesc
            val txticms: TextView = binding.icmsSpValue
            val txticmsForaSp: TextView = binding.icmsForaValue
            val txtncm: TextView = binding.ncmValue
            val txtValor: TextView = binding.valor
            val txtipi: TextView = binding.ipiValue
            val txtFornecedor: TextView = binding.fornecedor
            val txtcodigo: TextView = binding.codigoValue


            val nf: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

            txtFornecedor.text = produto.grupo
            txtValor.text = nf.format(produto.valorunitario).toString()
            txticmsForaSp.text = produto.icmsforasp.toString()
            txticms.text = produto.icmssp.toString()
            txtncm.text = produto.ncm?.toLong().toString()
            txtipi.text = produto.ipi.toString()
            txtcodigo.text = produto.codigo
            txtDesc.text = produto.nome
            Picasso.get().load(produto.imagem).into(imgDesc)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}