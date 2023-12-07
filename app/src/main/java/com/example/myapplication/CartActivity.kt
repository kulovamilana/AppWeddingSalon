package com.example.myapplication

import CartAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recyclerViewCart)
        cartAdapter = CartAdapter(cartItems)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = cartAdapter

        // Check if there are extras in the Intent
        if (intent.extras != null) {
            val itemName = intent.getStringExtra(EXTRA_CART_ITEM_NAME)
            val itemPrice = intent.getStringExtra(EXTRA_CART_ITEM_PRICE)
            val itemImageResId = intent.getIntExtra(EXTRA_CART_ITEM_IMAGE_RES_ID, 0)

            // Create a CartItem and add it to the list
            val cartItem = CartItem(itemName.orEmpty(), itemPrice.orEmpty(), itemImageResId)
            cartItems.add(cartItem)

            // Notify the adapter of the change
            cartAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val EXTRA_CART_ITEM_NAME = "extra_cart_item_name"
        const val EXTRA_CART_ITEM_PRICE = "extra_cart_item_price"
        const val EXTRA_CART_ITEM_IMAGE_RES_ID = "extra_cart_item_image_res_id"
    }
}