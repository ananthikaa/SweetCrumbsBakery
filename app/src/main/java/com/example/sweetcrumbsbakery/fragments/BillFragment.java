package com.example.sweetcrumbsbakery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sweetcrumbsbakery.MainActivity;
import com.example.sweetcrumbsbakery.R;
import com.example.sweetcrumbsbakery.model.BillResponse;
import com.example.sweetcrumbsbakery.model.CartItem;
import com.example.sweetcrumbsbakery.network.ApiService;
import com.example.sweetcrumbsbakery.network.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillFragment extends Fragment {

    private TextView tvSubtotal, tvDiscount, tvTax, tvFinalAmount, tvOffer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvDiscount = view.findViewById(R.id.tvDiscount);
        tvTax = view.findViewById(R.id.tvTax);
        tvFinalAmount = view.findViewById(R.id.tvFinalAmount);
        tvOffer = view.findViewById(R.id.tvOffer);

        loadBill();
    }

    private void loadBill() {
        MainActivity activity = (MainActivity) requireActivity();

        List<CartItem> cartItems = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : activity.getCart().entrySet()) {
            cartItems.add(new CartItem(entry.getKey(), entry.getValue()));
        }

        Map<String, List<CartItem>> request = new HashMap<>();
        request.put("items", cartItems);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        apiService.calculateBill(request).enqueue(new Callback<BillResponse>() {
            @Override
            public void onResponse(Call<BillResponse> call, Response<BillResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showBill(response.body());
                } else {
                    Toast.makeText(requireContext(), "Failed to load bill", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BillResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showBill(BillResponse bill) {
        tvSubtotal.setText("₹" + bill.getSubtotal());
        tvDiscount.setText("- ₹" + bill.getDiscount());
        tvTax.setText("₹" + bill.getTax());
        tvFinalAmount.setText("₹" + bill.getFinalAmount());

        if (bill.getDiscount() > 0) {
            tvOffer.setText("🎉 " + bill.getSavingsMessage() + "\n" + bill.getOfferApplied());
        } else {
            tvOffer.setText("Add items worth ₹1000 or more to unlock 10% off.");
        }
    }
}