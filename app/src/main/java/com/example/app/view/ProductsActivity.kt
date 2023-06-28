package com.example.app.view

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.util.RecyclerViewAdapter
import com.example.app.R
import com.example.app.databinding.ActivityProductsBinding
import com.example.app.model.Products
import org.json.JSONObject


class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var searchView: SearchView
    private lateinit var products: MutableList<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setando a barra de ações
        val toolbar: Toolbar = binding.homeToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Produtos"

        // Setando a barra de pesquisa por nome
        searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Convertendo um json para String e jogando na lista de produtos para mostrar na recyclerView
        val produtosJSon = this.assets.readAssetsFile("produtos.json")
        products = products(produtosJSon)

        // Configurando a recyclerView para mostrar a lista de produtos
        recyclerView = binding.rvMain
        recyclerViewAdapter = RecyclerViewAdapter(products)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.onItemClick = {
            val intent = Intent(this, ProductDescriptionActivity::class.java)
            intent.putExtra("products", it)
            startActivity(intent)
        }

    }

    // Função que filtra a pesquisa de produtos
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = mutableListOf<Products>()
            for (i in products) {
                if (i.nome.lowercase().contains(query.lowercase()) || i.grupo.lowercase().contains(query.lowercase())) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_LONG).show()
            } else {
                recyclerViewAdapter.setFilteredList(filteredList)
            }
        }
    }

    // Função que faz a leitura do JSon da pasta assets e retorna como string
    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }

    // Função que da funcionalidade para flecha, ao clica-la retorna para a tela anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    // Função que faz a leitura do JSon como string e retorna uma lista do tipo produto
    private fun products(produtosJSon: String): MutableList<Products> {
        val produtos = mutableListOf<Products>()
        val jsonRoot = JSONObject(produtosJSon)
        val jsonProdutos = jsonRoot.getJSONArray("produtos")

        for (i in 0 until jsonProdutos.length()) {
            val jsonProduto = jsonProdutos.getJSONObject(i)

            val img = jsonProduto.getString("imagem")
            val cod = jsonProduto.getString("codigo")
            val nome = jsonProduto.getString("nome")
            val valorUnitario = jsonProduto.getDouble("valor unitario")
            val grupo = jsonProduto.getString("grupo")
            val ncm = jsonProduto.getDouble("ncm")
            val ipi = jsonProduto.getDouble("IPI %")
            val icms = jsonProduto.getDouble("ICMS SP %")
            val icmsFora = jsonProduto.getDouble("ICMS Fora SP %")

            produtos.add(Products(img, cod, nome, valorUnitario, grupo, ncm, ipi, icms, icmsFora))
        }
        return produtos
    }
}