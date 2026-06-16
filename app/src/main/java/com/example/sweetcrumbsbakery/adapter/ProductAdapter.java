package com.example.sweetcrumbsbakery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcrumbsbakery.R;
import com.example.sweetcrumbsbakery.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<Product> productList;
    private final ProductClickListener listener;

    public ProductAdapter(List<Product> productList, ProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        int qty = listener.getProductQuantity(product.getId());

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("₹" + product.getPrice());

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
            holder.ivProduct.setImageResource(imageRes);
        } else {
            holder.ivProduct.setImageResource(R.drawable.croissant);
        }

        if (qty > 0) {
            holder.btnAdd.setVisibility(View.GONE);
            holder.qtyLayout.setVisibility(View.VISIBLE);
            holder.tvQty.setText(String.valueOf(qty));
        } else {
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.qtyLayout.setVisibility(View.GONE);
        }

        holder.btnAdd.setOnClickListener(v -> {
            listener.onAddClicked(product);
            notifyItemChanged(holder.getAdapterPosition());
        });

        holder.btnPlus.setOnClickListener(v -> {
            listener.onPlusClicked(product);
            notifyItemChanged(holder.getAdapterPosition());
        });

        holder.btnMinus.setOnClickListener(v -> {
            listener.onMinusClicked(product);
            notifyItemChanged(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvName;
        TextView tvPrice;
        TextView tvQty;
        Button btnAdd;
        Button btnPlus;
        Button btnMinus;
        LinearLayout qtyLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);

            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);

            qtyLayout = itemView.findViewById(R.id.qtyLayout);
        }
    }
}