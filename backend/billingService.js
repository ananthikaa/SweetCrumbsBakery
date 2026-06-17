const products = require("./products");

function calculateBill(cartItems) {
  let subtotal = 0;
  let discount = 0;
  let billItems = [];

  cartItems.forEach(cartItem => {
    const product = products.find(p => p.id === cartItem.id);

    if (!product) {
      throw new Error("Invalid product ID");
    }

    if (cartItem.quantity <= 0) {
      throw new Error("Quantity must be greater than zero");
    }

    const itemTotal = product.price * cartItem.quantity;

    billItems.push({
      id: product.id,
      name: product.name,
      price: product.price,
      quantity: cartItem.quantity,
      total: itemTotal
    });

    subtotal += itemTotal;
  });

  let offerApplied = "No offer applied";

  if (subtotal >= 1000) {
    discount = subtotal * 0.10;
    offerApplied = "10% discount applied for bill above ₹1000";
  }

  const taxableAmount = subtotal - discount;
  const tax = taxableAmount * 0.05;
  const finalAmount = taxableAmount + tax;

  return {
    items: billItems,
    subtotal: Number(subtotal.toFixed(2)),
    discount: Number(discount.toFixed(2)),
    tax: Number(tax.toFixed(2)),
    finalAmount: Number(finalAmount.toFixed(2)),
    offerApplied,
    savingsMessage: discount > 0
      ? `You saved ₹${discount.toFixed(2)} today!`
      : "Add more items to unlock bakery offers."
  };
}

module.exports = { calculateBill };