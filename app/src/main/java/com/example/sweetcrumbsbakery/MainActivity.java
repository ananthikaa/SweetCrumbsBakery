package com.example.sweetcrumbsbakery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetcrumbsbakery.fragments.BillFragment;
import com.example.sweetcrumbsbakery.fragments.CartFragment;
import com.example.sweetcrumbsbakery.fragments.MenuFragment;
import com.example.sweetcrumbsbakery.model.Product;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final Map<Integer, Integer> cart = new HashMap<>();
    private final Map<Integer, Product> productMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMenuFragment();
    }

    public void addToCart(Product product) {
        productMap.put(product.getId(), product);

        int qty = cart.containsKey(product.getId())
                ? cart.get(product.getId())
                : 0;

        cart.put(product.getId(), qty + 1);
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public int getCartCount() {
        int total = 0;

        for (int qty : cart.values()) {
            total += qty;
        }

        return total;
    }

    public void showMenuFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new MenuFragment())
                .commit();
    }

    public void showCartFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new CartFragment())
                .addToBackStack(null)
                .commit();
    }

    public void showBillFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new BillFragment())
                .addToBackStack(null)
                .commit();
    }

    public void increaseCartItem(Product product) {
        addToCart(product);
    }

    public void decreaseCartItem(Product product) {
        int id = product.getId();

        if (!cart.containsKey(id)) return;

        int qty = cart.get(id);

        if (qty <= 1) {
            cart.remove(id);
            productMap.remove(id);
        } else {
            cart.put(id, qty - 1);
        }
    }

    public int getProductQuantity(int productId) {
        if (cart.containsKey(productId)) {
            return cart.get(productId);
        }
        return 0;
    }
}