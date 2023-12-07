package com.example.myapplication

import ProductAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myImageView = findViewById<ImageView>(R.id.imageView2)
        val imageView = findViewById<ImageView>(R.id.imageView3)

        myImageView.setOnClickListener {
            goInfoActivity(it)
        }

        imageView.setOnClickListener {
            goContactsActivity(it)
        }


        val productList = listOf(
            Product("Свадебное платье", "60 000", R.drawable.dress4),
            Product("Украшение", "2 345", R.drawable.jewerly),
            Product("Свадебное платье", "55 000", R.drawable.dress2),
            Product("Фата", "2 100", R.drawable.fata1),
            Product("Свадебное платье", "62 100", R.drawable.dress5),
            Product("Украшение", "2 820", R.drawable.crown),
            Product("Фата", "1 200", R.drawable.fata2),
            Product("Украшение", "3 240", R.drawable.crown7),
            Product("Украшение", "2 120", R.drawable.jewerly1),
            Product("Фата", "1 289", R.drawable.fata6),
            Product("Фата", "2 301", R.drawable.fata5),
            Product("Украшение", "1 890", R.drawable.crown4),
            Product("Фата", "1 100", R.drawable.fata3),
            Product("Украшение", "1 560", R.drawable.crown8),
            Product("Свадебное платье", "55 000", R.drawable.dress2),
            Product("Украшение", "2 280", R.drawable.jewerly2),
            Product("Фата", "1 650", R.drawable.fata7),
            Product("Украшение", "1 920", R.drawable.crown6),
            Product("Фата", "980", R.drawable.fata4),
            Product("Украшение", "1 920", R.drawable.jewerly3),
            Product("Фата", "3 040", R.drawable.fata8),
        )

        recyclerView = findViewById(R.id.recyclerView)
        productAdapter = ProductAdapter( productList, this)

        val layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productAdapter
    }

    override fun onItemClick(product: Product) {

    }

    override fun onAddToCartClick(product: Product) {

        val intent = Intent(this, BookActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun goInfoActivity(view: View) {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    fun goContactsActivity(view: View) {
        val intent = Intent(this, ContactsActivity::class.java)
        startActivity(intent)
    }

}
