const express = require("express");
const cors = require("cors");
const products = require("./products");
const { calculateBill } = require("./billingService");

const app = express();

app.use(cors());
app.use(express.json());

app.get("/", (req, res) => {
  res.send("Sweet Crumbs Bakery API is running");
});

app.get("/products", (req, res) => {
  res.json(products);
});

app.post("/calculate-bill", (req, res) => {
  try {
    const { items } = req.body;

    if (!items || !Array.isArray(items) || items.length === 0) {
      return res.status(400).json({
        error: "Cart items are required"
      });
    }

    const bill = calculateBill(items);
    res.json(bill);

  } catch (error) {
    res.status(400).json({
      error: error.message
    });
  }
});

const PORT = process.env.PORT || 5000;

app.listen(PORT, "0.0.0.0", () => {
    console.log(`Server running on port ${PORT}`);
});