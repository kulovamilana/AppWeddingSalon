import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Product
import com.example.myapplication.R

class ProductAdapter(
    private val productList: List<Product>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(product: Product)
        fun onAddToCartClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        private val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        private val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
        private val buttonAddToCart: View = itemView.findViewById(R.id.buttonAddToCart)

        fun bind(product: Product) {
            imageViewProduct.setImageResource(product.imageResId)
            textViewProductName.text = product.name
            textViewProductPrice.text = "${product.price}"

            itemView.setOnClickListener {
                listener.onItemClick(product)
            }

            buttonAddToCart.setOnClickListener {
                listener.onAddToCartClick(product)
            }
        }
    }
}
