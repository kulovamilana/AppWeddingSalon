package com.example.myapplication

import ProductAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val productList = listOf(
            Product("Свадебное платье", "60 000", R.drawable.dress),
            Product("Свадебное платье", "55 000", R.drawable.dress2),
            Product("Свадебное платье", "64 000", R.drawable.dress3),
            Product("Свадебное платье", "60 000", R.drawable.dress4),

            // Добавьте больше продуктов, если нужно
        )

        recyclerView = findViewById(R.id.recyclerView)
        productAdapter = ProductAdapter(productList, this)

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productAdapter
    }

    override fun onItemClick(product: Product) {
        // Start InformationActivity with product details
        val intent = Intent(this, InformationActivity::class.java).apply {
            putExtra(InformationActivity.EXTRA_PRODUCT_NAME, product.name)
            putExtra(InformationActivity.EXTRA_PRODUCT_PRICE, product.price)
            putExtra(InformationActivity.EXTRA_PRODUCT_IMAGE_RES_ID, product.imageResId)
        }
        startActivity(intent)
    }

    override fun onAddToCartClick(product: Product) {
        // Start InformationActivity with product details
        val intent = Intent(this, InformationActivity::class.java).apply {
            putExtra(InformationActivity.EXTRA_PRODUCT_NAME, product.name)
            putExtra(InformationActivity.EXTRA_PRODUCT_PRICE, product.price)
            putExtra(InformationActivity.EXTRA_PRODUCT_IMAGE_RES_ID, product.imageResId)
        }
        startActivity(intent)
    }
}
