package com.example.sweetcrumbsbakery.model;

public class CartItem {

    private int id;
    private int quantity;

    public CartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}