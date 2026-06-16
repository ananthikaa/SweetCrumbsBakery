package com.example.sweetcrumbsbakery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcrumbsbakery.MainActivity;
import com.example.sweetcrumbsbakery.R;
import com.example.sweetcrumbsbakery.adapter.ProductAdapter;
import com.example.sweetcrumbsbakery.adapter.ProductClickListener;
import com.example.sweetcrumbsbakery.model.Product;
import com.example.sweetcrumbsbakery.network.ApiService;
import com.example.sweetcrumbsbakery.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment implements ProductClickListener {

    private RecyclerView rvMenuProducts;
    private TextView tvCartCount;
    private Button btnViewCart;
    private ApiService apiService;

    public MenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        rvMenuProducts = view.findViewById(R.id.rvMenuProducts);
        tvCartCount = view.findViewById(R.id.tvCartCount);
        btnViewCart = view.findViewById(R.id.btnViewCart);

        rvMenuProducts.setLayoutManager(
                new GridLayoutManager(requireContext(), 2));

        apiService = RetrofitClient
                .getInstance()
                .create(ApiService.class);

        loadProducts();

        updateCartCount();

        btnViewCart.setOnClickListener(v ->
                ((MainActivity) requireActivity()).showCartFragment());
    }

    private void loadProducts() {

        apiService.getProducts().enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call,
                                   Response<List<Product>> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    rvMenuProducts.setAdapter(
                            new ProductAdapter(
                                    response.body(),
                                    MenuFragment.this
                            ));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call,
                                  Throwable t) {

                Toast.makeText(
                        requireContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public void onAddClicked(Product product) {

        ((MainActivity) requireActivity())
                .addToCart(product);

        updateCartCount();

    }

    private void updateCartCount() {

        int count =
                ((MainActivity) requireActivity())
                        .getCartCount();

        tvCartCount.setText(
                "Cart Items: " + count
        );
    }

    @Override
    public void onPlusClicked(Product product) {
        ((MainActivity) requireActivity()).increaseCartItem(product);
        updateCartCount();
    }

    @Override
    public void onMinusClicked(Product product) {
        ((MainActivity) requireActivity()).decreaseCartItem(product);
        updateCartCount();
    }

    @Override
    public int getProductQuantity(int productId) {
        return ((MainActivity) requireActivity()).getProductQuantity(productId);
    }
}