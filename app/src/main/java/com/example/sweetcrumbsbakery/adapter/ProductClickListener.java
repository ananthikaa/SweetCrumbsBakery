package com.example.sweetcrumbsbakery.adapter;

import com.example.sweetcrumbsbakery.model.Product;

public interface ProductClickListener {
    void onAddClicked(Product product);
    void onPlusClicked(Product product);
    void onMinusClicked(Product product);
    int getProductQuantity(int productId);
}