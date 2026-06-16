package com.example.sweetcrumbsbakery.adapter;

import com.example.sweetcrumbsbakery.model.Product;

public interface CartClickListener {
    void onCartPlusClicked(Product product);
    void onCartMinusClicked(Product product);
}