package com.example.myapplication

import CartAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InformationActivity : AppCompatActivity(), ProductDetailFragment.OnAddToCartListener {
    private val cartItems = mutableListOf<CartItem>()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        // Initialize RecyclerView and CartAdapter with an empty list
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCart)
        cartAdapter = CartAdapter(cartItems)  // Pass the list of cart items
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Check if the Intent has extras
        if (intent.extras != null) {
            val productName = intent.getStringExtra(EXTRA_PRODUCT_NAME)
            val productPrice = intent.getStringExtra(EXTRA_PRODUCT_PRICE)
            val productImageResId = intent.getIntExtra(EXTRA_PRODUCT_IMAGE_RES_ID, 0)

            val fragment = ProductDetailFragment.newInstance(
                productName.orEmpty(),
                productPrice.orEmpty(),
                productImageResId
            )

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    override fun onAddToCart(productName: String, productPrice: String, productImageResId: Int) {
        // Perform the desired action when the item is added to the cart
        showSnackbar("Товар добавлен в корзину: $productName")

        // Add the item to the RecyclerView's adapter
        val cartItem = CartItem(productName, productPrice, productImageResId)
        cartAdapter.addItem(cartItem)

        // Finish the current activity (closing the fragment)
        finish()

        // Start CartActivity and pass the selected product information
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra(CartActivity.EXTRA_CART_ITEM_NAME, productName)
        intent.putExtra(CartActivity.EXTRA_CART_ITEM_PRICE, productPrice)
        intent.putExtra(CartActivity.EXTRA_CART_ITEM_IMAGE_RES_ID, productImageResId)
        startActivity(intent)
    }

    private fun showSnackbar(message: String) {
        // Implement Snackbar logic here
    }

    companion object {
        const val EXTRA_PRODUCT_NAME = "extra_product_name"
        const val EXTRA_PRODUCT_PRICE = "extra_product_price"
        const val EXTRA_PRODUCT_IMAGE_RES_ID = "extra_product_image_res_id"
    }
}