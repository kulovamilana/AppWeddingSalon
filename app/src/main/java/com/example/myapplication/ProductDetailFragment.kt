import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.example.myapplication.R

class ProductDetailFragment : Fragment() {

    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productImageView: ImageView
    private lateinit var addToCartButton: Button

    // Define an interface to communicate with the hosting activity
    interface OnAddToCartListener {
        fun onAddToCart(productName: String, productPrice: String, productImageResId: Int)
    }

    private lateinit var addToCartListener: OnAddToCartListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Attach the hosting activity to the listener
        if (context is OnAddToCartListener) {
            addToCartListener = context
        } else {
            throw RuntimeException("$context must implement OnAddToCartListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        productNameTextView = view.findViewById(R.id.textViewProductName)
        productPriceTextView = view.findViewById(R.id.textViewProductPrice)
        productImageView = view.findViewById(R.id.imageViewProduct)
        addToCartButton = view.findViewById(R.id.buttonAddToCart)

        // Получение данных о товаре из аргументов
        val productName = arguments?.getString(ARG_PRODUCT_NAME)
        val productPrice = arguments?.getString(ARG_PRODUCT_PRICE)
        val productImageResId = arguments?.getInt(ARG_PRODUCT_IMAGE_RES_ID)

        // Установка данных в элементы интерфейса
        productNameTextView.text = productName
        productPriceTextView.text = productPrice
        productImageView.setImageResource(productImageResId ?: R.drawable.dress)

        // Обработчик события для кнопки "В корзину"
        addToCartButton.setOnClickListener {
            // Notify the hosting activity to add the product to the cart
            addToCartListener.onAddToCart(
                productNameTextView.text.toString(),
                productPriceTextView.text.toString(),
                productImageResId ?: 0
            )

            // Display a Snackbar to indicate that the product was added to the cart
            showSnackbar("Товар добавлен в корзину")
        }

        return view
    }

    private fun showSnackbar(message: String) {
        view?.let {
            val snackbar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(resources.getColor(R.color.green)) // Replace R.color.green with your green color resource
            snackbar.show()
        }
    }

    companion object {
        const val ARG_PRODUCT_NAME = "product_name"
        const val ARG_PRODUCT_PRICE = "product_price"
        const val ARG_PRODUCT_IMAGE_RES_ID = "product_image_res_id"

        fun newInstance(productName: String, productPrice: String, productImageResId: Int): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putString(ARG_PRODUCT_NAME, productName)
            args.putString(ARG_PRODUCT_PRICE, productPrice)
            args.putInt(ARG_PRODUCT_IMAGE_RES_ID, productImageResId)
            fragment.arguments = args
            return fragment
        }
    }
}
