package com.example.sweetcrumbsbakery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcrumbsbakery.R;
import com.example.sweetcrumbsbakery.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<Integer> productIds;
    private final Map<Integer, Integer> cart;
    private final Map<Integer, Product> productMap;
    private final CartClickListener listener;

    public CartAdapter(Map<Integer, Integer> cart,
                       Map<Integer, Product> productMap,
                       CartClickListener listener) {
        this.cart = cart;
        this.productMap = productMap;
        this.listener = listener;
        this.productIds = new ArrayList<>(cart.keySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {

        int id = productIds.get(position);

        Product product = productMap.get(id);
        int qty = cart.get(id);

        holder.tvCartName.setText(product.getName());
        holder.tvCartPrice.setText("₹" + product.getPrice() + " × " + qty);
        holder.tvCartQty.setText(String.valueOf(qty));

        String imageName = product.getName()
                .toLowerCase()
                .replace(" ", "_");

        int imageRes = holder.itemView
                .getContext()
                .getResources()
                .getIdentifier(
                        imageName,
                        "drawable",
                        holder.itemView.getContext().getPackageName()
                );

        if (imageRes != 0) {
            holder.ivCartProduct.setImageResource(imageRes);
        } else {
            holder.ivCartProduct.setImageResource(R.drawable.croissant);
        }

        holder.btnCartPlus.setOnClickListener(v -> {
            listener.onCartPlusClicked(product);
            notifyDataSetChanged();
        });

        holder.btnCartMinus.setOnClickListener(v -> {
            listener.onCartMinusClicked(product);
            productIds.clear();
            productIds.addAll(cart.keySet());
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return productIds.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCartProduct;
        TextView tvCartName;
        TextView tvCartPrice;
        TextView tvCartQty;
        Button btnCartPlus;
        Button btnCartMinus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCartProduct = itemView.findViewById(R.id.ivCartProduct);
            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            tvCartQty = itemView.findViewById(R.id.tvCartQty);

            btnCartPlus = itemView.findViewById(R.id.btnCartPlus);
            btnCartMinus = itemView.findViewById(R.id.btnCartMinus);
        }
    }
}