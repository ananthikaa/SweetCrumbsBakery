package com.example.sweetcrumbsbakery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcrumbsbakery.MainActivity;
import com.example.sweetcrumbsbakery.R;
import com.example.sweetcrumbsbakery.adapter.CartAdapter;
import com.example.sweetcrumbsbakery.adapter.CartClickListener;
import com.example.sweetcrumbsbakery.model.Product;

public class CartFragment extends Fragment implements CartClickListener {

    private RecyclerView rvCart;
    private Button btnProceedBill;
    private CartAdapter cartAdapter;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        rvCart = view.findViewById(R.id.rvCart);
        btnProceedBill = view.findViewById(R.id.btnProceedBill);

        activity = (MainActivity) requireActivity();

        rvCart.setLayoutManager(new LinearLayoutManager(requireContext()));

        cartAdapter = new CartAdapter(
                activity.getCart(),
                activity.getProductMap(),
                this
        );

        rvCart.setAdapter(cartAdapter);

        btnProceedBill.setOnClickListener(v -> activity.showBillFragment());
    }

    @Override
    public void onCartPlusClicked(Product product) {
        activity.increaseCartItem(product);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCartMinusClicked(Product product) {
        activity.decreaseCartItem(product);
        cartAdapter.notifyDataSetChanged();

        if (activity.getCart().isEmpty()) {
            activity.showMenuFragment();
        }
    }
}