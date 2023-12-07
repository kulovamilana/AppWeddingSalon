import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CartItem
import com.example.myapplication.R

class CartAdapter(private val cartItems: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun addItem(cartItem: CartItem) {
        cartItems.add(cartItem)
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.textViewCartItemName)
        private val itemPriceTextView: TextView = itemView.findViewById(R.id.textViewCartItemPrice)

        fun bind(cartItem: CartItem) {
            itemNameTextView.text = cartItem.name
            itemPriceTextView.text = cartItem.price
        }
    }
}
