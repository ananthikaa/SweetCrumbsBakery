package com.example.sweetcrumbsbakery.model;

public class BillResponse {

    private double subtotal;
    private double discount;
    private double tax;
    private double finalAmount;
    private String offerApplied;
    private String savingsMessage;

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getOfferApplied() {
        return offerApplied;
    }

    public String getSavingsMessage() {
        return savingsMessage;
    }
}